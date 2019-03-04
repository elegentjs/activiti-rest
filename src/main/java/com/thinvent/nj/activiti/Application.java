package com.thinvent.nj.activiti;

import org.activiti.spring.boot.DataSourceProcessEngineAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

/**
 * 应用程序入口
 * @author liupeijun
 */
@SpringBootApplication(exclude = {
        MybatisAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.activiti.spring.boot.SecurityAutoConfiguration.class,
        DataSourceProcessEngineAutoConfiguration.class
})
@ComponentScan("com.thinvent.nj")
@ActiveProfiles("dev")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
