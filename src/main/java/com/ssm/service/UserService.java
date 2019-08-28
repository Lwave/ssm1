package com.ssm.service;


import com.ssm.entity.User;
import com.ssm.entity.UserExample;

import java.util.List;

public interface UserService {

    long getCount(UserExample example);

    int del(User user);

    User select(Integer id);

    List<User> selectAll(UserExample example);
}
