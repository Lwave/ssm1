package com.ssm.service.impl;

import com.ssm.dao.UserMapper;
import com.ssm.entity.User;
import com.ssm.entity.UserExample;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
@Autowired
    UserMapper userMapper;
    @Override
    public long getCount(UserExample example) {
        return userMapper.countByExample(example);
    }
}
