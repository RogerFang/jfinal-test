package com.demo.common.shiro;

import com.demo.common.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Roger on 2016/8/7.
 */
public class UserRealm extends AuthorizingRealm{

    /**
     * 授权查询回调
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        String username = userToken.getUsername();

        User user = User.dao.findFirst("select * from user where user.username = ?", username);

        if (user == null){
            // 没找到账号
            throw new UnknownAccountException();
        }

        if (Boolean.TRUE.equals(user.getLocked())){
            // 账号锁定
            throw new LockedAccountException();
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName());
        return info;
    }
}
