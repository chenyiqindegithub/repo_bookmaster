package com.example.boot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.net.URLEncoder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.entity.Book;
import com.example.boot.entity.User;
import com.example.boot.service.BookBorrowService;
import com.example.boot.service.BookService;
import com.example.boot.service.UserService;
import com.example.boot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.boot.common.Result;
import org.springframework.web.multipart.MultipartFile;
import com.example.boot.entity.BookBorrow;

import org.springframework.web.bind.annotation.RestController;

/**
* BookBorrowAPI接口
*/
@RestController
@RequestMapping("/bookBorrow")
public class BookBorrowController {

    @Resource
    private BookBorrowService bookBorrowService;
    @Resource
    private UserService userService;
    @Resource
    BookService bookService;

    @PostMapping
    public Result save(@RequestBody BookBorrow bookBorrow) {
        bookBorrow.setDate(DateUtil.today());
        bookBorrow.setBack("否");
        User currentUser = TokenUtils.getCurrentUser();
        if (ObjectUtil.isNull(currentUser)) {
            return Result.error("token失效，请重新登录");
        }
        bookBorrow.setUserId(currentUser.getId());
        bookBorrow.setUsername(currentUser.getName());
        bookBorrowService.save(bookBorrow);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody BookBorrow bookBorrow) {
        if ("通过".equals(bookBorrow.getStatus())) {
            // 更新图书库存信息
            Book book = bookService.getById(bookBorrow.getBookId());
            int left = book.getStore() - bookBorrow.getNum();
            if (left < 0) {
                return Result.error("图书库存不足");
            }
            book.setStore(left);
            bookService.updateById(book);
        }
        bookBorrowService.updateById(bookBorrow);
        return Result.success();
    }

    @PutMapping("/returnBook")
    public Result returnBook(@RequestBody BookBorrow bookBorrow) {
        Book book = bookService.getById(bookBorrow.getBookId());
        if (book == null) {
            return Result.error("图书不存在");
        }
        book.setStore(book.getStore() + bookBorrow.getNum());
        bookService.updateById(book);
        bookBorrow.setBack("是");
        bookBorrowService.updateById(bookBorrow);

        // 对应用户积分增加10
        User user = userService.getById(bookBorrow.getUserId());
        user.setAccount(user.getAccount() + 10);
        userService.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        bookBorrowService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        bookBorrowService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(bookBorrowService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(bookBorrowService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<BookBorrow> queryWrapper = new QueryWrapper<BookBorrow>().orderByDesc("id");
        List<Book> bookList = bookService.list();
        if (StrUtil.isNotBlank(name)) {
            List<Integer> ids = bookList.stream().filter(b -> b.getName().contains(name)).map(Book::getId).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(ids)) {
                queryWrapper.in("book_id", ids);
            } else {
                queryWrapper.in("book_id", 0);
            }
        }
        if ("USER".equals(TokenUtils.getCurrentUser().getRole())) {
            queryWrapper.eq("user_id", TokenUtils.getCurrentUser().getId());
        }
        Page<BookBorrow> page = bookBorrowService.page(new Page<>(pageNum, pageSize), queryWrapper);

        for (BookBorrow record : page.getRecords()) {
            record.setBookName(bookList.stream().filter(book -> book.getId().equals(record.getBookId())).findFirst().orElse(new Book()).getName());
            record.setBookImg(bookList.stream().filter(book -> book.getId().equals(record.getBookId())).findFirst().orElse(new Book()).getImg());
        }
        return Result.success(page);
    }

    /**
    * 导出接口
    */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<BookBorrow> list = bookBorrowService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("BookBorrow信息表", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
    * excel 导入
    * @param file
    * @throws Exception
    */
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<BookBorrow> list = reader.readAll(BookBorrow.class);
        list.forEach(o -> bookBorrowService.save(o));
        return Result.success();
    }

}
