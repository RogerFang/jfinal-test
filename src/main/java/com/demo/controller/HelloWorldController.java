package com.demo.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * Created by Roger on 2016/8/6.
 */
public class HelloWorldController extends Controller {
    public void index(){
        renderText("JFinal: hello world!");
    }

    // @ActionKey("/sayHello")
    public void sayHello(){
        renderText("hello");
        System.out.println("test action key");
    }
}
