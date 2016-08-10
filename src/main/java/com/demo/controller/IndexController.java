package com.demo.controller;

import com.jfinal.core.Controller;

/**
 * Created by Roger on 2016/8/6.
 */
public class IndexController extends Controller {

    public void index(){
        // renderText(IndexController.class.getCanonicalName());
        render("index.jsp");
    }

    public void saveMsg(){
        String msg = getPara("msg");
        System.out.println(msg);
        renderJson("success", Boolean.TRUE);
    }
}
