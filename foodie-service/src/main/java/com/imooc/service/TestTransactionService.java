package com.imooc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestTransactionService {
    @Autowired
    StuService stuService;


    /**
     * REQUIRED:支持事务，如果事务不存在，那么创建一个事务；如果当前存在一个事务，那么加入此事务
     * SUPPORTS: 支持事务， 存在事务，按事务执行，如没有那么按非事务执行
     * MANDATORY: 支持当前事务，如不存在，那么抛出异常
     * REQUIRES_NEW: 创建一个事务，如果存在当前事务，那么将当前事务挂起
     * NOT_SUPPORTED : 不按事务的方式，如果存在当前事务，那么挂起当前事务
     * NEVER: 不支持事务，如存在当前事务，那么抛出异常
     * NESTED: 如果存在当前事务，那么产生子事务（嵌套事务），如果不存在当前事务，，那么痛required
     * 如果主事务存在，那么子事务随着主事务 一同提交或回滚
     * 如果子事务回滚，主事务可根据需要回滚或提交 ； 如主事务提交，那么需要将子事务代码块用try catch 控制，且不抛出异常
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testTransaction (){
        stuService.saveParent();
        try{stuService.saveChild();}catch(Exception e){

        }
       // int s = 1/0;
    }



}
