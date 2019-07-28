package com.hwx.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Huawei Xie
 * @date: 2019/7/27
 */
@RestController
public class UserController {
    @GetMapping("/getUser")
    public String getUser() {
        return "success";
    }
}
