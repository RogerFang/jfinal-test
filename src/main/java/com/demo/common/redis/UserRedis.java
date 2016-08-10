package com.demo.common.redis;

import com.demo.common.constant.UserConst;
import com.demo.common.model.User;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

/**
 * Created by Roger on 2016/8/8.
 */
public class UserRedis {

    public static Cache getCache(){
        return Redis.use(UserConst.USER_TABLE);
    }

    public static void cacheUser(User user){
        Cache cache = getCache();
        cache.set(user.getUsername(), user);
    }

    public static User getFromCache(String username){
        return getCache().get(username);
    }

    public static User getFromCache(Integer id){
        Cache cache = getCache();
        return cache.get(id);
    }

    public static void removeFromCache(Integer id){
        Cache cache = getCache();
        cache.del(id);
    }
}
