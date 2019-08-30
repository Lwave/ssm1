package com.ssm.controller;

import com.ssm.entity.UserExample;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EchartsController {
    @Autowired
    private UserService userService;

    @RequestMapping("/pie")//默认请求方式为get
    public String pie1() {
        return "pie";
    }

    @RequestMapping(value = "/pie", method = RequestMethod.POST)//处理异步请求
    @ResponseBody
    public List<Map<String, Object>> pie2(Model model) {
        List<Map<String, Object>> ls = new ArrayList<>();
        UserExample userExample = new UserExample();
        List<UserExample.Criteria> oredCriteria = userExample.getOredCriteria();
        UserExample.Criteria or = userExample.or();
        or.andStatusEqualTo(1);
        oredCriteria.add(or);
        long num = userService.getCount(userExample);
        System.out.println(num);
        //每一组数据---一个Map对象
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name", "启用用户");
        map1.put("value", num);
        ls.add(map1);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name", "未启用用户");
        List<UserExample.Criteria> o = userExample.getOredCriteria();
        o.clear();
        UserExample.Criteria or1 = userExample.or();
        or1.andStatusEqualTo(0);
        o.add(or1);
        long num1 = userService.getCount(userExample);
        System.out.println(num1);
        map2.put("value", num1);
        ls.add(map2);
        return ls;
    }

    @RequestMapping("/bar")
    public String bar1() {
        return "bar";
    }

    @RequestMapping(value = "/bar", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> bar2() {
        List<Map<String, Object>> ma = new ArrayList<>();
        UserExample userExample = new UserExample();
        userExample.setDistinct(true);
        userExample.setOrderByClause("createdate desc");//日期降序
        List<UserExample.Criteria> oredCriteria = userExample.getOredCriteria();
        UserExample.Criteria or = userExample.or();
        //String---->Date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long count = userService.getCount(userExample);

        //每一组数据---一个Map对象
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name", "日期");
    /*    map1.put("value", );*/

        HashMap<String, Object> map2 = new HashMap<>();
        ma.add(map2);

        HashMap<String, Object> map3 = new HashMap<>();
        ma.add(map3);

        HashMap<String, Object> map4 = new HashMap<>();
        ma.add(map4);

        return ma;

    }

}
