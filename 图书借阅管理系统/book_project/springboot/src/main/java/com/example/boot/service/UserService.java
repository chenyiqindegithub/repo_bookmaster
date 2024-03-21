package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.entity.User;
import com.example.boot.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
