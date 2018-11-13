package com.thinvent.nj.activiti.factory;

import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.uc.entity.Role;
import com.thinvent.nj.uc.service.UserService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MyGroupEntityManager extends GroupEntityManager {

    @Autowired
    private UserService userService;


    @Override
    public List<Group> findGroupsByUser(String userId) {
        List<Group> groups = new ArrayList<>();
        com.thinvent.nj.uc.entity.User user = userService.get(userId);
        List<String> roleKeys = user.getRoleList().parallelStream().map(Role:: getCode).collect(Collectors.toList());

        Group group;
        for (String roleKey : roleKeys) {
            group = new GroupEntity(roleKey);
            group.setType("assignment");
            groups.add(group);
        }

        return groups;
    }

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
        if (!StringUtil.isNullOrEmpty(query.getId())) {
            Group group = new GroupEntity(query.getId());

            return Arrays.asList(group);
        }

        if (!StringUtil.isNullOrEmpty(query.getUserId())) {
            return findGroupsByUser(query.getUserId());
        }


        throw new UnsupportedOperationException();
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNewGroup(Group group) {
        throw new UnsupportedOperationException();
    }
}
