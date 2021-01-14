package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//被容器扫描到
@Configuration
public class CorsConfig {
public CorsConfig (){

}
@Bean
public CorsFilter corsFilter(){
    CorsConfiguration config = new CorsConfiguration();
    config.addAllowedOrigin("http://localhost:8080");
    //设置是否发送cookie信息
    config.setAllowCredentials(true);
    //设置允许请求的方式
    config.addAllowedMethod("*");
    //设置允许的header
    config.addAllowedHeader("*");
    //2.为URL添加映射信息
    UrlBasedCorsConfigurationSource  corsSource = new UrlBasedCorsConfigurationSource();
    corsSource.registerCorsConfiguration("/**",config);
    //重新设置
    return new CorsFilter(corsSource);
}


}
