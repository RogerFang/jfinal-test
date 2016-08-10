package com.demo.common.service;

import com.demo.common.model.User;
import com.demo.common.redis.UserRedis;

/**
 * Created by Roger on 2016/8/8.
 */
public class UserService {
    public static UserService userService = new UserService();

    public User findByUsername(String username){
        User user = UserRedis.getFromCache(username);

        if (user != null){
            return user;
        }

        user = User.dao.findFirst("select * from user where username = ?", username);

        if (user != null){
            UserRedis.cacheUser(user);
        }

        return user;
    }
}
