package com.imooc.api.test;

import com.imooc.service.StuService;
import com.imooc.service.TestTransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = com.imooc.SpringbootApplication.class)
public class StuServiceTest {
   // @Autowired
    TestTransactionService service;

   // @Test
    public void testStuService() {
        service.testTransaction();
    }

}
