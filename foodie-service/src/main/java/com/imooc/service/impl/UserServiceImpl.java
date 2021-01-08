package com.imooc.service.impl;

import com.imooc.common.enums.Sex;
import com.imooc.common.utils.DateUtil;
import com.imooc.common.utils.MD5Utils;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private  static  final  String USER_FACE= "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    /**
     * @Description 判断用户名是否存在
     * @param username
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public boolean queryUsernameIsExists(String username) {
        Example example = new Example(Users.class);
        Example.Criteria userCriteria = example.createCriteria();
        userCriteria.andLike("username",username);
        Users result =  usersMapper.selectOneByExample(example);
        return result == null ? false : true;
    }

    /**
     * @Description  创建用户信息
     * @param userBO  代表接收前端数据的数据模
     * @see  com.imooc.pojo.bo.UserBO;
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public Users createUser(UserBO userBO) {
        String id = sid.nextShort();
        Users users = new Users();
        users.setId(id);
        users.setUsername(userBO.getUsername());
        try {
            users.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        }catch (Exception e){
            e.printStackTrace();
        }
        //默认用户名为简名称
        users.setNickname(userBO.getUsername());
        //默认用户头像
        users.setFace(USER_FACE);
        //默认用户的生日
        users.setBirthday(DateUtil.stringToDate("1900-01-01"));
        //设置默认性别
        users.setSex(Sex.secret.type);
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        //设置user id
        usersMapper.insert(users);
        return users;
    }
}
