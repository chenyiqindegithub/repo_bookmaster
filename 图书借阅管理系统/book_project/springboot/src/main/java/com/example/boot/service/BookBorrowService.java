package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.entity.BookBorrow;
import com.example.boot.mapper.BookBorrowMapper;
import org.springframework.stereotype.Service;

@Service
public class BookBorrowService extends ServiceImpl<BookBorrowMapper, BookBorrow> {

}
