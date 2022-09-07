package com.oven.service;

import java.util.List;

public interface UserPermissionService {

    List<String> findPermissionsByUid(Integer uid);

}