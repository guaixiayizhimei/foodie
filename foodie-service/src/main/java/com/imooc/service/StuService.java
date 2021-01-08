package com.imooc.service;

import com.imooc.mapper.StuMapper;
import com.imooc.pojo.Stu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @Description 学生服务层
 * @date 2020年12月9日10:03:02
 */

@Service
public class StuService {

    @Autowired
    private StuMapper stuMapper;

    /**
     * @param id
     * @return stu
     * @Description 根据学生ID 获取学生信息
     * @see com.imooc.pojo.Stu;
     */

    @Transactional(propagation = Propagation.SUPPORTS)
    public Stu getStuById(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    /**
     * @param stu
     * @Description 新增学生，主键自增
     * @see com.imooc.pojo.Stu;
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveStu(Stu stu) {
        stuMapper.insert(stu);
        return "新增成功！";
    }

    /**
     * @param stu
     * @return java.lang.String
     * @Description 根据主键修改学生信息
     * @see com.imooc.pojo.Stu;
     */
    public String updateStu(Stu stu) {
        stuMapper.updateByPrimaryKey(stu);
        return "修改成功！";
    }

    /**
     * @param id
     * @Description 根据主键删除学生信息
     */
    public String deleteStu(int id) {
        stuMapper.deleteByPrimaryKey(id);
        return "删除成功！";
    }

    public void saveParent() {
        Stu stu = new Stu();
        stu.setAge(20);
        stu.setName("parent");
        stuMapper.insert(stu);
    }

    /**
     * 测试spring事务传播范围 required, 即支持一个事务，如果没有则创建一个事务,如果存在当前事务，那么加入这个事务;
     * 举例：领导没饭吃，我有钱，自己买了自己吃
     * supports : 即支持事务，如果当前不存在事务，那么按非事务执行
     * mandatory: 即支持事务，如果当前没有事务，那么抛出异常
     * required_new : 即创建一个事务，如果当前存在事务，那么将当前事务挂起
     */
    @Transactional(propagation = Propagation.NESTED)
    public void saveChild() {
            Stu stu = new Stu();
            stu.setAge(20);
            stu.setName("child1");
            stuMapper.insert(stu);
            int s = 1/0;
            Stu stu1 = new Stu();
            stu1.setAge(20);
            stu1.setName("child2");
            stuMapper.insert(stu1);
    }


    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTransaction() {
        saveParent();
        saveChild();
       //int p = 1 / 0;
    }



}
