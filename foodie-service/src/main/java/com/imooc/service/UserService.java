package com.imooc.service;

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
}
