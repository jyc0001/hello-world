package com.jyc.rtodemo.service;

import com.jyc.rtodemo.mapper.UserMapper;
import com.jyc.rtodemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByJdpin(long jdpin){
        return userMapper.selectUserByJdpin(jdpin);
    }

}
