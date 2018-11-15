package com.thinvent.nj.activiti.factory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义的GroupEntityManagerFactory
 * @author liupeijun 
 */
@Component
public class GroupEntityManagerFactory implements SessionFactory {

    @Autowired
    private MyGroupEntityManager groupEntityManager;

    @Override
    public Class<?> getSessionType() {
        return GroupIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return groupEntityManager;
    }
}
