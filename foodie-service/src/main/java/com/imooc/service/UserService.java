package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

/**
 * @author zhaocan
 * @date 2021年1月8日14:32:14
 */
public interface UserService {
    /**
     * @Description  判断用户是否存在
     * @param username 用户登录名
     * @return boolean
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * @Description  创建用户
     * @param userBO  代表接收前端数据的数据模型 @link com.i
     * @return
     */
    public Users  createUser(UserBO userBO);

    /**
     * @Description  用于登录查询用户信息
     * @param userBO
     * @return
     */
    public Users queryUsersForLogin(String username ,String password);
}
