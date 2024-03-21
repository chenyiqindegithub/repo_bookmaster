package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.entity.BookBorrow;
import com.example.boot.entity.GoodsExchange;
import com.example.boot.mapper.BookBorrowMapper;
import com.example.boot.mapper.GoodsExchangeMapper;
import org.springframework.stereotype.Service;

@Service
public class GoodsExchangeService extends ServiceImpl<GoodsExchangeMapper, GoodsExchange> {

}
