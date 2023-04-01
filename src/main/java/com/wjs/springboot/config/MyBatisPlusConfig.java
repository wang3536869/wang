package com.wjs.springboot.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wjs
 */
@Configuration
public class MyBatisPlusConfig {
   @Bean
   public PaginationInterceptor paginationInterceptor (){
       return new PaginationInterceptor();
   }
}
