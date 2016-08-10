package com.demo.controller;

import com.demo.common.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

import java.io.IOException;

/**
 * Created by Roger on 2016/8/7.
 */
public class LoginController extends Controller{

    @Before(GET.class)
    public void index(){
        render("login.jsp");
    }

    @ActionKey("/signin")
    @Before(POST.class)
    public void signin() throws IOException {
        String username = getPara("username");
        String password = getPara("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        String error = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
            e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            error = "用户名/密码错误";
            e.printStackTrace();
        } catch (LockedAccountException e){
            error = "用户被锁定";
            e.printStackTrace();
        } catch (AuthenticationException e){
            error = "其他错误: " + e.getMessage();
            e.printStackTrace();
        }

        if (error == null){
            // String url = WebUtils.getRequestUri(getRequest());
            // System.out.println(url);
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(getRequest());
            String p_uri = savedRequest.getRequestURI();
            redirect(p_uri);
        }else {
            System.out.println(error);
            redirect("/login");
        }
    }
}
