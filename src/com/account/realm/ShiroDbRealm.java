package com.account.realm;

import com.account.domain.User;
import com.account.service.UserService;
import com.account.utils.CipherUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

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
        CipherUtil cipherUtil = new CipherUtil();//MD5加密
        if (user!=null){
            return new SimpleAuthenticationInfo(user.getName(),cipherUtil.generateName(user.getName()),getName());
        }else{
            throw new AuthenticationException();
        }

    }
     /*
        登录成功后进行的角色以及权限验证
         */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*根据userName使用role和permission的service层来做判断 对应的权限添加*/
        Set<String> roleNames = new HashSet<String>();
        Set<String> permission = new HashSet<String>();
        roleNames.add("");//添加角色对应到index
        permission.add("");//添加权限对应到index
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permission);
        return info;
    }

    /*
        清除用户授权信息
     */
    public void clearCachedAuthorizationInfo(String principal){
        SimplePrincipalCollection principalCollection = new SimplePrincipalCollection(principal,getName());
        clearCachedAuthorizationInfo(principalCollection);
    }

    /*
        清除所有用户授权信息
     */
    public void clearAllCachedAuthorizationInfo(){
        Cache<Object,AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null){
            for (Object key:cache.keys()){
                cache.remove(key);
            }
        }
    }
}
