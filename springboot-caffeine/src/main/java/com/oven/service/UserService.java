package com.oven.service;

import com.oven.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 用户服务层
 */
@Slf4j
@Service
public class UserService {

    @CachePut(value = "user", key = "#user.id")
    public User save(User user) {
        log.info("操作数据库，保存用户，{}", user.toString());
        log.info("添加key为[{}]的缓存", user.getId());
        return user;
    }

    @CacheEvict(value = "user", key = "#id")
    public void delete(Integer id) {
        log.info("添加key为[{}]的缓存", id);
    }

    @Cacheable(value = "user", key = "#id", sync = true)
    public User getById(Integer id) {
        log.info("操作数据库，进行通过ID查询，ID: {}", id);
        User user = new User();
        user.setId(id);
        user.setAge(18);
        user.setUserName("Oven");
        log.info("添加key为[{}]的缓存", id);
        return user;
    }

}
