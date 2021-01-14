package com.imooc.controller;

import com.imooc.common.utils.CookieUtils;
import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.common.utils.JsonUtils;
import com.imooc.common.utils.MD5Utils;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.application.Application;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaocan3
 * @date 2021年1月12日11:20:33
 */
@RestController
@RequestMapping("/passport")
@Api(value ="注册登录" , tags = {"用于注册登录的接口"})
public class PassportController {

    @Autowired
    private UserService userService;
    @ApiOperation(value = "用户名是否存在",notes = "判断用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
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
    //ApiOperation value : 接口名  notes ：接口说明  httpMethod 请求的方式
    @ApiOperation(value = "用户注册",notes = "用于用户注册", httpMethod = "POST")
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

    @ApiOperation(value = "用户登录" ,notes = "用于用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request , HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        //判断用户名，密码，确认密码不能为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) ) {
            //用户名不能为空
            return IMOOCJSONResult.errorMsg("用户名密码不能为空");
        }
        try{
            password = MD5Utils.getMD5Str(password);
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询用户信息
        Users user = userService.queryUsersForLogin(username,password);
        if (user==null){
            return  IMOOCJSONResult.errorMsg("用户名密码不正确");
        }
        user = setNullProperty(user);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(user),true);
        //请求成功，返回用户信息
        return IMOOCJSONResult.ok(user);
    }

    /**
     * @Description  设置敏感信息为空
     * @param user
     * @return
     */
    private  Users  setNullProperty(Users user){
        if(user==null) {return  null;}
        user.setBirthday(null);
        user.setPassword(null);
        user.setEmail(null);
        user.setMobile(null);
        user.setUpdatedTime(null);
        user.setCreatedTime(null);
        return  user;
    }
}
