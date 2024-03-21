package com.example.boot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.common.Result;
import com.example.boot.entity.Goods;
import com.example.boot.entity.GoodsExchange;
import com.example.boot.entity.User;
import com.example.boot.service.GoodsExchangeService;
import com.example.boot.service.GoodsService;
import com.example.boot.service.UserService;
import com.example.boot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

/**
* GoodsExchangeAPI接口
*/
@RestController
@RequestMapping("/goodsExchange")
public class GoodsExchangeController {

    @Resource
    private GoodsExchangeService goodsExchangeService;
    @Resource
    private UserService userService;
    @Resource
    GoodsService goodsService;

    @PostMapping
    public Result save(@RequestBody GoodsExchange goodsExchange) {
        User currentUser = TokenUtils.getCurrentUser();
        if (ObjectUtil.isNull(currentUser)) {
            return Result.error("token失效，请重新登录");
        }
        Goods goods = goodsService.getById(goodsExchange.getGoodsId());
        if (goods.getStore() < goodsExchange.getNum()) {
            return Result.error("商品库存不足");
        }
        if (currentUser.getAccount() < goods.getPrice() * goodsExchange.getNum()) {
            return Result.error("您的积分不足");
        }
        goodsExchange.setDate(DateUtil.today());
        goodsExchange.setUserId(currentUser.getId());
        goodsExchange.setUsername(currentUser.getName());
        goodsExchange.setAccount(goods.getPrice() * goodsExchange.getNum());
        goodsExchangeService.save(goodsExchange);

        goods.setStore(goods.getStore() - goodsExchange.getNum());
        goodsService.updateById(goods);

        currentUser.setAccount(currentUser.getAccount() - goods.getPrice() * goodsExchange.getNum());
        userService.updateById(currentUser);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody GoodsExchange goodsExchange) {
        goodsExchangeService.updateById(goodsExchange);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        goodsExchangeService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        goodsExchangeService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(goodsExchangeService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(goodsExchangeService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<GoodsExchange> queryWrapper = new QueryWrapper<GoodsExchange>().orderByDesc("id");
        List<Goods> goodsList = goodsService.list();
        if (StrUtil.isNotBlank(name)) {
            List<Integer> ids = goodsList.stream().filter(b -> b.getName().contains(name)).map(Goods::getId).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(ids)) {
                queryWrapper.in("goods_id", ids);
            } else {
                queryWrapper.in("goods_id", 0);
            }
        }
        if ("USER".equals(TokenUtils.getCurrentUser().getRole())) {
            queryWrapper.eq("user_id", TokenUtils.getCurrentUser().getId());
        }
        Page<GoodsExchange> page = goodsExchangeService.page(new Page<>(pageNum, pageSize), queryWrapper);

        for (GoodsExchange record : page.getRecords()) {
            record.setGoodsName(goodsList.stream().filter(goods -> goods.getId().equals(record.getGoodsId())).findFirst().orElse(new Goods()).getName());
            record.setGoodsImg(goodsList.stream().filter(goods -> goods.getId().equals(record.getGoodsId())).findFirst().orElse(new Goods()).getImg());
        }
        return Result.success(page);
    }

    /**
    * 导出接口
    */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<GoodsExchange> list = goodsExchangeService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("GoodsExchange信息表", "UTF-8");
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
        List<GoodsExchange> list = reader.readAll(GoodsExchange.class);
        list.forEach(o -> goodsExchangeService.save(o));
        return Result.success();
    }

}
