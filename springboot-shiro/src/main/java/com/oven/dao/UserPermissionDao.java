package com.oven.dao;

import com.oven.entity.UserPermission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserPermissionDao extends CrudRepository<UserPermission, Long> {

    List<UserPermission> findByUid(Integer uid);

}