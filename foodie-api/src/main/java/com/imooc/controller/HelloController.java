package com.imooc.controller;

import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Administrator
 */

/**
 * ApiIgnore 忽略此controller， 不被swagger扫描到
 */
@RestController
@ApiIgnore
public class HelloController {
    private  static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    public  Object  helloRequest(){
        logger.info("info");
        logger.error("error");
        logger.warn("warning");
        logger.debug("debug");
        return  "Hello word !!";
    }
}
