package com.imooc.controller;

import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试相关功能
 * @author Administrator
 */
@RestController
public class StuController {

    @Autowired
    private StuService stuService;



    @GetMapping("/getOrderById")
    public String  getOrderById(int id){

        return  stuService.getStuById(id).toString();
    }

    @GetMapping("/saveOrder")
    public String  saveOrder(){
        Stu stu = new Stu();
        stu.setName("张三");
        stu.setAge(18);
        return stuService.saveStu(stu);
    }


    @GetMapping("/updateOrder")
    public String  updateOrder(){
        Stu stu = new Stu();
        stu.setName("李四");
        stu.setAge(18);
        stu.setId(2);
        return stuService.updateStu(stu);
    }

    @GetMapping("/deleteOrder")
    public String  deleteOrder(){
        return stuService.deleteStu(1);
    }

    @GetMapping("/transactions")
    public String  transactions() throws Exception {
        stuService.saveTransaction();
        return  "成功";
    }

}
