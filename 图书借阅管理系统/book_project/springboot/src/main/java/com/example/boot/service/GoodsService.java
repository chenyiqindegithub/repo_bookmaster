package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.entity.Goods;
import com.example.boot.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {

}
