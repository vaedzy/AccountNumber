package com.account.controller;

import com.account.domain.User;
import com.account.realm.ShiroDbRealm;
import com.account.service.UserService;
import com.account.utils.CipherUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
    @Autowired
    private UserService userService;
    /*
    验证springmvc 与 mybatis连接
     */
    @RequestMapping("/{id}/showUser")
    public String showUser(@PathVariable int id, HttpServletRequest request){
        User user = userService.getUserById(id);
        System.out.println(user.getName());
        request.setAttribute("user",user);
        return "showUser";
    }
    /*
       初始登陆页面
     */
    @RequestMapping("login.do")
    public String tologin(HttpServletRequest request , HttpServletResponse response , Model model){
        logger.debug("ip["+request.getRemoteHost());
        return "login";
    }
    /*
    验证用户是否存在
     */
    @RequestMapping("/checkLogin.do")
    public String login(HttpServletRequest request){
        String result="login.do";
        //获得用户手机号并加密
        String username = CipherUtil.generateName(request.getParameter("username"));
        //获得验证码
        String code = request.getParameter("code");
        UsernamePasswordToken token = new UsernamePasswordToken(username,code);
        Subject User = SecurityUtils.getSubject();
        try{
            System.out.println("-----");
            if (!User.isAuthenticated()){
                token.setRememberMe(true);
                User.login(token);
            }
            System.out.println(result);
            result="index";
        }catch (Exception e ){
            logger.error(e.getMessage());
            result="login.do";
        }
        return result;

    }
}
