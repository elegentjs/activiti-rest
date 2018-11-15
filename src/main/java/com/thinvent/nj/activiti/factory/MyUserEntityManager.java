package com.thinvent.nj.activiti.factory;

import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.uc.entity.Role;
import com.thinvent.nj.uc.service.UserService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义UserEntityManager, 用于替换默认的UserEntityManager
 * 放弃使用activiti自带的用户角色管理，接入uc系统的用户角色管理
 *
 */
@Component
public class MyUserEntityManager extends UserEntityManager {

    @Autowired
    private UserService userService;

    @Override
    public User findUserById(String userId) {

        if (StringUtil.isNullOrEmpty(userId)) {
            return null;
        }

        com.thinvent.nj.uc.entity.User user = userService.get(userId);
        User result = new UserEntity(userId);
        result.setFirstName(user.getFullName());
        result.setEmail(user.getEmail());

        return result;
    }


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
    public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
        if (!StringUtil.isNullOrEmpty(query.getId())) {
            User user = findUserById(query.getId());

            return Arrays.asList(user);
        }


        throw new UnsupportedOperationException();
    }


    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean checkPassword(String userId, String password) {
        return true;
    }

    @Override
    public List<User> findPotentialStarterUsers(String proceDefId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNewUser(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Picture getUserPicture(String userId) {
        throw new UnsupportedOperationException();
    }
}