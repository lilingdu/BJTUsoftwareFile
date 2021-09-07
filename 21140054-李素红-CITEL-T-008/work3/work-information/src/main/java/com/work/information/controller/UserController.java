package com.work.information.controller;

import com.work.information.entity.User;
import com.work.information.service.UserService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: miki
 * @Date: 2021/07/24/8:28
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    //查询所有
    @GetMapping("all")
    public List<User> selectAll() {
        //System.out.println(this.userService.selectAll());
        return this.userService.selectAll();
    }
    //根据id删除
    @GetMapping("delete")
    public void deleteById(WebRequest request){
        int id = Integer.valueOf(request.getParameter("id").trim());
        this.userService.deleteById(id);
    }
    //更新数据
    @RequestMapping(value ="update",method={RequestMethod.POST})
    public void update(@RequestBody User userInfo){
        this.userService.update(userInfo);
    }
    //插入数据
    @PostMapping("add")
    //public @ResponseBody String add(@RequestBody User addInfo, Model model, HttpServletResponse response) throws IOException {
    public ResponseEntity<Void>  add(@RequestBody User addInfo, Model model, HttpServletResponse response) throws IOException {
        this.userService.addUser(addInfo);
        return ResponseEntity.ok().build();
       /* int id = addInfo.getId();
        User result = this.userService.findById(id);
        //判断id是否已在数据库中
        if (result==null) {
            this.userService.addUser(addInfo);
            //return "新增成功";
            return ResponseEntity.ok().build();
        }else{
            model.addAttribute("msg","id已经存在，请确认后再输入1。");
            System.out.println("The id is already in database.");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            // 直接响应数据
            //response.getWriter().print("id已经存在，请确认后再输入。");
            //return "id已经存在，请确认后再输入。";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }*/

    }
    //根据年份查询
    @GetMapping("year")
    public List<User> queryByYear(WebRequest request) {
         String str= request.getParameter("scope").trim();
         String[] args = str.split("-");
         int year1 =Integer.valueOf( args[0]);
         int year2 =args.length>1?Integer.valueOf( args[1]):Integer.valueOf( args[0]);
         //判断输入区间的范围大小
         int temp = 0;
         if(year1>year2){
             temp = year1;
             year1= year2;
             year2 = temp;
         }
         //System.out.println(this.userService.queryByYear(year1,year2));
         return this.userService.queryByYear(year1,year2);
    }
    //按飞行里程查询
    @GetMapping("mile")
    public List<User> queryByMile(WebRequest request) {
        String str= request.getParameter("scope").trim();
        String[] args = str.split("-");
        int mile1 =Integer.valueOf( args[0]);
        int mile2 =args.length>1?Integer.valueOf( args[1]):Integer.valueOf( args[0]);
        //System.out.println(this.userService.queryByMile(mile1,mile2));
        int temp = 0;
        if(mile1>mile2){
            temp = mile1;
            mile1= mile2;
            mile2 = temp;
        }
        return this.userService.queryByMile(mile1,mile2);
    }
    //按飞行里程查询
    @GetMapping("time")
    public List<User> queryByTime(WebRequest request) {
        String str= request.getParameter("scope").trim();
        String[] args = str.split("-");
        int time1 =Integer.valueOf( args[0]);
        int time2 =args.length>1?Integer.valueOf( args[1]):Integer.valueOf( args[0]);
        int temp = 0;
        if(time1>time2){
            temp = time1;
            time1= time2;
            time2 = temp;
        }
        return this.userService.queryByTime(time1,time2);
    }
}
