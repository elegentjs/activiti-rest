package com.thinvent.nj.activiti.factory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义的UserEntityManagerFactory
 */
@Component
public class UserEntityManagerFactory implements SessionFactory {

    @Autowired
    private MyUserEntityManager userEntityManager;


    @Override
    public Class<?> getSessionType() {
        return MyUserEntityManager.class;
    }

    @Override
    public Session openSession() {
        return userEntityManager;
    }
}
