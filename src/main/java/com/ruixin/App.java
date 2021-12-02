package com.ruixin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;



/**
 * Created by ruixin on 2017/5/19.
 * Description:springboot启动类
 */
@SpringBootApplication
//扫描该包下的class，主要是mybatis的持久化类
@EnableCaching
@MapperScan({"com.ruixin.dao", "com.database.mapper"})
public class App {

    /**
     * 启动主方法
     * @param args
     */
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
