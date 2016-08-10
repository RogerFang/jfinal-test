package com.demo.handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Roger on 2016/8/8.
 */
public class SessionHandler extends Handler {

    @Override
    public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, boolean[] booleen) {
        int index = s.lastIndexOf(";JSESSIONID");
        s = index==-1?s:s.substring(0, index);
        next.handle(s, httpServletRequest, httpServletResponse, booleen);
    }
}
