package com.ssm.controller;

import com.ssm.entity.User;
import com.ssm.entity.UserExample;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/count")
    public String count(String name, String start, String end, Model model) {
        UserExample userExample = new UserExample();
        userExample.setDistinct(true);
        userExample.setOrderByClause("createdate desc");//日期降序
        List<UserExample.Criteria> oredCriteria = userExample.getOredCriteria();
        UserExample.Criteria or = userExample.or();
        //String---->Date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date startDate = simpleDateFormat.parse(start);
            Date endDate = simpleDateFormat.parse(end);
            or.andCreatedateBetween(startDate, endDate);
            or.andLoginnameLike("%" + name + "%");
            oredCriteria.add(or);
            long num = userService.getCount(userExample);
            model.addAttribute("num",num);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "user";
    }
}

