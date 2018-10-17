package com.thinvent.nj.activiti;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

/**
 * 应用程序入口
 * @author liupeijun
 */
@SpringBootApplication(exclude = {
        MybatisAutoConfiguration.class
})
@ComponentScan("com.thinvent.nj")
public class Application {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }
}
