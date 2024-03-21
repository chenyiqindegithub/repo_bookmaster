package com.example.boot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.boot.common.Result;
import com.example.boot.config.AuthAccess;
import com.example.boot.entity.Book;
import com.example.boot.entity.BookBorrow;
import com.example.boot.entity.User;
import com.example.boot.mapper.UserMapper;
import com.example.boot.service.BookBorrowService;
import com.example.boot.service.BookService;
import com.example.boot.utils.TokenUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class WebController {

    @Resource
    UserMapper userMapper;

    @Resource
    BookService bookService;

    @Resource
    BookBorrowService bookBorrowService;

    @GetMapping("/")
    @AuthAccess
    public Result success() {
        return Result.success();
    }

    @GetMapping("/nums")
    public Result nums() {
        Dict dict = Dict.create();
        List<User> users = userMapper.selectList(new QueryWrapper<User>().eq("role", "USER"));
        dict.set("user", users.size());

        List<Book> bookList = bookService.list();
        Integer store = bookList.stream().map(Book::getStore).reduce(Integer::sum).orElse(0);
        dict.set("store", store);

        List<BookBorrow> bookBorrowList = bookBorrowService.list(new QueryWrapper<BookBorrow>().eq("date", DateUtil.today()).eq("back", "否"));
        dict.set("borrow", bookBorrowList.stream().map(BookBorrow::getNum).reduce(Integer::sum).orElse(0));
        // 未归还的图书
        Integer borrowSum = bookBorrowList.stream().filter(bookBorrow -> bookBorrow.getBack().equals("否")).map(BookBorrow::getNum).reduce(Integer::sum).orElse(0);
        dict.set("book", store + borrowSum);
        return Result.success(dict);
    }

    @GetMapping("/pie")
    public Result pie() {
        List<Book> bookList = bookService.list();
        List<BookBorrow> bookBorrowList = bookBorrowService.list();
        for (BookBorrow record : bookBorrowList) {
            record.setBookName(bookList.stream().filter(book -> book.getId().equals(record.getBookId())).findFirst().orElse(new Book()).getName());
            record.setBookImg(bookList.stream().filter(book -> book.getId().equals(record.getBookId())).findFirst().orElse(new Book()).getImg());
        }
        Set<String> collect = bookBorrowList.stream().map(BookBorrow::getBookName).filter(StrUtil::isNotBlank).collect(Collectors.toSet());
        List<Dict> dicts = CollUtil.newArrayList();
        for (String bookName : collect) {
            dicts.add(Dict.create().set("name", bookName).set("value", bookBorrowList.stream().filter(bookBorrow ->
                    bookName.equals(bookBorrow.getBookName())).map(BookBorrow::getNum).reduce(Integer::sum).orElse(0)));
        }
        return Result.success(dicts);
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @AuthAccess
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error("参数错误");
        }
        User dbUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (dbUser == null || !user.getPassword().equals(dbUser.getPassword())) {
            return Result.error("用户名或密码错误");
        }
        if (dbUser.getDisable()) {
            return Result.error("禁止登录");
        }
        String token = TokenUtils.genToken(String.valueOf(dbUser.getId()), dbUser.getPassword());
        dbUser.setToken(token);
        dbUser.setPassword(null);
        return Result.success(dbUser);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error("参数错误");
        }
        User dbUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (dbUser != null) {
            return Result.error("用户名已存在");
        }
        if (StrUtil.isBlank(user.getName())) {
            user.setName("小可爱" + RandomUtil.randomNumbers(6) + "号");
        }
        user.setRole("USER");
        userMapper.insert(user);
        return Result.success();
    }

}
