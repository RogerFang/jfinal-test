package com.demo.controller;

import com.demo.common.model.User;
import com.demo.common.service.UserService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by Roger on 2016/8/6.
 */
public class UserController extends Controller {

    public void index(){
        renderText(UserController.class.getCanonicalName());
    }

    public void get(){
        // User user = User.dao.findById(1);
        // renderJson(user);
        String username = getPara(0);
        System.out.println(username);
        System.out.println(getPara(1));
        System.out.println(getPara(2));
        User user = UserService.userService.findByUsername(username);
        renderJson(user);
    }

    public void getPartAttr(){
        // 查询id=1, 且仅仅取出部分字段值
        User user = User.dao.findByIdLoadColumns(1, "username, interest, create_time");
        renderJson(user);
    }

    public void getPerAttr(){
        User user = User.dao.findById(1);
        String username = user.getStr("username");
        renderJson(username);
    }

    public void update(){
        User.dao.findById(1).set("username", "fanglong").update();
        renderJson(User.dao.findById(1));
    }

    public void getBySql(){
        List<User> users = User.dao.find("select * from user where age > 18");
        renderJson(users);
    }

    public void getByPage(){
        Page<User> userPage = User.dao.paginate(1, 10, "select *", "from user where age > ?", 18);
        renderJson(userPage);
    }
}
