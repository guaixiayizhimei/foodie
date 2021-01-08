package com.imooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public  Object  helloRequest(){
        return  "Hello word !!";
    }
}
