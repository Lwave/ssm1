package com.ssm.controller;


import com.ssm.entity.ExcelBean;
import com.ssm.entity.User;
import com.ssm.entity.UserExample;
import com.ssm.service.UserService;
import com.ssm.utils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class UploadController {
    @Autowired
    UserService userService;

    @RequestMapping("/upload")
    public String file() {

        return "list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String upload(MultipartFile myfile, HttpSession session) {
        String fn = myfile.getOriginalFilename();
        System.out.println(fn);
        //上传文件存储位置：tomcat部署项目的位置files
        String path = session.getServletContext().getRealPath("files");
        System.out.println(path);
        try {
            myfile.transferTo(new File(path + "/" + fn));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "pie";
    }

    @RequestMapping(value = "/getExcelData", method = RequestMethod.POST)
    public String upload(MultipartFile myfile, Model model) {

        try {
            List<List<Object>> lists = ExcelUtil.getUserListByExcel(myfile.getInputStream(), myfile.getOriginalFilename());
            List<User> users = new ArrayList<>();
            for (int i = 0; i < lists.size(); i++) {
                User user = new User();
                List<Object> obj = lists.get(i);
                user.setId(Integer.parseInt(obj.get(0).toString()));
                user.setLoginname(obj.get(1).toString());
                user.setPassword(obj.get(2).toString());
                user.setStatus(Integer.parseInt(obj.get(3).toString()));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date time = sdf.parse(obj.get(4).toString());
                user.setCreatedate(time);
                user.setUsername(obj.get(5).toString());
                users.add(user);
            }

            //插入数据
            int n = userService.insert(users);

        } catch (Exception e) {
            e.printStackTrace();
        }
        UserExample userExample = new UserExample();
        List<User> user = userService.selectByAll(userExample);
        model.addAttribute("all", user);

        return "list";
    }

    @RequestMapping(value = "export", method = RequestMethod.POST)
    @ResponseBody
    public void exportUser(HttpServletRequest req, HttpServletResponse reps) throws IOException, Exception {
        reps.reset(); // 清除buffer缓存
        // 指定下载的文件名，浏览器都会使用本地编码，即GBK，浏览器收到这个文件名后，用ISO-8859-1来解码，然后用GBK来显示
        // 所以我们用GBK解码，ISO-8859-1来编码，在浏览器那边会反过来执行。
        reps.setHeader("Content-Disposition",
                "attachment;filename=" + new String("用户一览表.xlsx".getBytes("GBK"), "UTF-8"));
        reps.setContentType("application/vnd.ms-excel;charset=UTF-8");
        reps.setHeader("Pragma", "no-cache");
        reps.setHeader("Cache-Control", "no-cache");
        reps.setDateHeader("Expires", 0);
        UserExample example = new UserExample();
        List<User> userList = userService.selectAll(example);
        // 导出Excel对象
        OutputStream output;
        List<ExcelBean> excel = new ArrayList<>();
        Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
        //设置标题栏
        excel.add(new ExcelBean("编号", "id", 0));
        excel.add(new ExcelBean("登录名", "loginname", 0));
        excel.add(new ExcelBean("密码", "password", 0));
        excel.add(new ExcelBean("状态", "status", 0));
        excel.add(new ExcelBean("加入时间", "createdate", 0));
        excel.add(new ExcelBean("姓名", "username", 0));
        map.put(0, excel);
        String sheetName = "用户一览";
        //调用ExcelUtil的方法
        XSSFWorkbook workbook = ExcelUtil.createExcelFile(User.class, userList, map, sheetName);
        try {
            output = reps.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
