package com.oven.dao;

import com.oven.vo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    User findByUserName(String userName);

}