package com.oven.sevice.impl;

import com.oven.dao.UserPermissionDao;
import com.oven.entity.Menu;
import com.oven.entity.UserPermission;
import com.oven.service.MenuService;
import com.oven.service.UserPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Resource
    private MenuService menuService;
    @Resource
    private UserPermissionDao userPermissionDao;


    @Override
    public List<String> findPermissionsByUid(Integer uid) {
        List<UserPermission> userPermissions = userPermissionDao.findByUid(uid);
        List<String> result = new ArrayList<>();
        for (UserPermission userPermission : userPermissions) {
            Menu menu = menuService.findById(userPermission.getMid());
            result.add(menu.getPermissionCode());
        }
        return result;
    }
}