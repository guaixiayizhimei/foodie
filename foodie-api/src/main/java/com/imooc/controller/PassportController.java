package com.imooc.controller;

import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExists")
    public IMOOCJSONResult usernameIsExists(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            //用户名不能为空
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }

        boolean isExists = userService.queryUsernameIsExists(username);
        if (isExists) {
            //请求成功，用户名已存在
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        //请求成功，用户名不存在
        return IMOOCJSONResult.ok();

    }

    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();
        //判断用户名，密码，确认密码不能为空

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            //用户名不能为空
            return IMOOCJSONResult.errorMsg("用户名密码不能为空");
        }
        //判断密码长度不能小于6
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码长度不能小于6");
        }
        if (!password.equals(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("密码确认密码不一致");
        }
        //判断用户名是否存在
        boolean isExists = userService.queryUsernameIsExists(username);
        if (isExists) {
            //请求成功，用户名已存在
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        //创建用户
        Users users = userService.createUser(userBO);
        //请求成功，返回用户信息
        return IMOOCJSONResult.ok(users);
    }

}
