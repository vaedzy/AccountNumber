package com.account.realm;

import com.account.domain.User;
import com.account.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthorizingRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroDbRealm extends AuthorizingRealm{
    private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
    private static final String ALGORITHM ="MD5";
    @Autowired
    private UserService userService;
    public ShiroDbRealm(){
        super();
    }
    /*
    登录验证
    */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println(token.getUsername());
        User user = userService.findUserByName(token.getUsername());
        System.out.println(user);
        return null;
    }
}
