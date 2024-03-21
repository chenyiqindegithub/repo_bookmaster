package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.entity.Book;
import com.example.boot.mapper.BookMapper;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ServiceImpl<BookMapper, Book> {

}
