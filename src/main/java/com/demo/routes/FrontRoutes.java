package com.demo.routes;

import com.demo.controller.IndexController;
import com.demo.controller.UserController;
import com.jfinal.config.Routes;

/**
 * Created by Roger on 2016/8/6.
 */
public class FrontRoutes extends Routes {

    @Override
    public void config() {
        add("/", IndexController.class);
        add("/user", UserController.class);
    }
}
