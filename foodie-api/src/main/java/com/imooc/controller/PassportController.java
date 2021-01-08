package com.imooc.controller;

import com.imooc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExists")
    public  int  usernameIsExists(@RequestParam String username){
        if(StringUtils.isBlank(username)){
            //用户名不能为空
            return  500;
        }

        boolean isExists = userService.queryUsernameIsExists(username);
        if(isExists){
            //请求成功，用户名已存在
            return 500;
        }
        //请求成功，用户名不存在
        return 200;

    }


}
