package com.demo.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Roger on 2016/8/7.
 */
public class UserTest {
    @Test
    public void generateSalt() throws Exception {
        System.out.println(User.dao.generateSalt().length());
    }

    @Test
    public void entryptPassword() throws Exception {
        String salt = User.dao.generateSalt();
        String password = "admin";
        System.out.println("salt:" + salt);
        System.out.println("password:" + password);
        System.out.println("hashpassword:"+User.dao.entryptPassword(password, salt));
    }

}