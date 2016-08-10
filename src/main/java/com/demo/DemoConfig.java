package com.demo;

import com.demo.common.constant.UserConst;
import com.demo.common.model.User;
import com.demo.controller.HelloWorldController;
import com.demo.controller.LoginController;
import com.demo.handler.SessionHandler;
import com.demo.routes.FrontRoutes;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;

/**
 * Created by Roger on 2016/8/6.
 */
public class DemoConfig extends JFinalConfig {
    private Routes routes;

    @Override
    public void configConstant(Constants constants) {
        // constants.setDevMode(true);
        constants.setViewType(ViewType.JSP);
        constants.setBaseViewPath("/WEB-INF/");
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/helloworld", HelloWorldController.class);
        routes.add(new FrontRoutes());
        routes.add("/login", LoginController.class, "/");

        this.routes = routes;
    }

    @Override
    public void configPlugin(Plugins plugins) {
        loadPropertyFile("db.properties");
        DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbc.url"), getProperty("jdbc.username"), getProperty("jdbc.password"), getProperty("jdbc.driver"));
        plugins.add(druidPlugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        plugins.add(arp);
        arp.addMapping("user", User.class);

        // ShiroPlugin shiroPlugin = new ShiroPlugin(this.routes);
        // plugins.add(shiroPlugin);

        RedisPlugin userRedis = new RedisPlugin(UserConst.USER_TABLE, "localhost", 6379);
        plugins.add(userRedis);

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        // interceptors.add(new ShiroInterceptor());
    }

    @Override
    public void configHandler(Handlers handlers) {
        handlers.add(new SessionHandler());
    }
}
