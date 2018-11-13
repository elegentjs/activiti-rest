package com.thinvent.nj.activiti.conf;

import com.thinvent.nj.activiti.factory.GroupEntityManagerFactory;
import com.thinvent.nj.activiti.factory.UserEntityManagerFactory;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liupeijun
 */
@Configuration
public class MyCommonConfig {

    @Autowired
    private SpringProcessEngineConfiguration configuration;

    @Autowired
    private UserEntityManagerFactory userEntityManagerFactory;

    @Autowired
    private GroupEntityManagerFactory groupEntityManagerFactory;

    @PostConstruct
    public void setCustomSessionFactory() {
        List<SessionFactory> sessionFactories = new ArrayList<>(2);
        sessionFactories.add(userEntityManagerFactory);
        sessionFactories.add(groupEntityManagerFactory);

         configuration.setCustomSessionFactories(sessionFactories);
    }


}
