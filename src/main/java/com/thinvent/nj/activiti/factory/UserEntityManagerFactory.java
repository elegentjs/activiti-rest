package com.thinvent.nj.activiti.factory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
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
        return UserIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return userEntityManager;
    }
}
