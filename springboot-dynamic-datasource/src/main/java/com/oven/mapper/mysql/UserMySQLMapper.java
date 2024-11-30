package com.oven.mapper.mysql;

import com.oven.entity.User;

import java.util.List;

/**
 * UserMapper
 */
public interface UserMySQLMapper {

    List<User> findAll();

}
