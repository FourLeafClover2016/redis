package com.hwx.redis.controller;

import com.alibaba.fastjson.JSON;
import com.hwx.redis.entity.User;
import com.hwx.redis.util.CookisUtils;
import com.hwx.redis.util.RedisPoolUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author: Huawei Xie
 * @date: 2019/7/27
 */
@RestController()
public class Login {

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getSession().getId();
        CookisUtils.writeLoginToken(response, token);
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");
        String message = JSON.toJSONString(user);
        RedisPoolUtil.setEx(token, message, 30 * 60);
        return "login success";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getSession().getId();
        CookisUtils.delLoginToken(request, response);
        RedisPoolUtil.del(token);
        return "logout success";
    }

}
