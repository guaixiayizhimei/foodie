package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

/**
 * @author zhaocan
 * @date 2021年1月8日14:32:14
 */
public interface UserService {
    /**
     * @Description 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean queryUsernameIsExists(String username);

    /**
     * @Description  创建用户
     * @param userBO  代表接收前端数据的数据模型 @link com.i
     * @return
     */
    public Users  createUser(UserBO userBO);
}
