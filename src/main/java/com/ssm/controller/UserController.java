package com.ssm.controller;

import com.ssm.entity.User;
import com.ssm.entity.UserExample;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            model.addAttribute("num", num);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "user";
    }

    @RequestMapping("/delete")
    public ModelAndView delByPI() {
        User user = new User();
        user.setId(1);
        user.setStatus(0);
        int del = userService.del(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @RequestMapping("/select")
    public String sel(int id, Model model) {
        User user = userService.select(id);
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping("/all")
    public String selectAll(Model model) {
        UserExample userExample = new UserExample();
        List<User> u = userService.selectAll(userExample);
        model.addAttribute("u", u);
        return "user";
    }
}

