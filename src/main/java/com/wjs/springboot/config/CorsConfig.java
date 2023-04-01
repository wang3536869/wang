package com.wjs.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wjs
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 设置允许跨域的路由
                registry.addMapping("/**")
                        // 设置允许跨域请求的域名
                        .allowedOrigins("*")
                        // 再次加入前端Origin  localhost！=127.0.0.1
                        .allowedOrigins("http://localhost:8081")
                        // 是否允许证书（cookies）
                        .allowCredentials(true)
                        // 设置允许的方法
                        .allowedMethods("*")
                        //允许请求头
                        .allowedHeaders("*")
                        // 跨域允许时间
                        .maxAge(3600);
            }
        };
    }
}