package com.ssm.service.impl;

import com.ssm.dao.UserMapper;
import com.ssm.entity.User;
import com.ssm.entity.UserExample;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public long getCount(UserExample example) {
        return userMapper.countByExample(example);
    }

    @Override
    public int del(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User select(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Override
    public int insert(List<User> users) {
        int n = 0;
        for (User user : users) {
            n = userMapper.insertSelective(user);
        }
        return n;
    }

    @Override
    public List<User> selectByAll(UserExample example) {
        return userMapper.selectByExample(example);
    }


}
