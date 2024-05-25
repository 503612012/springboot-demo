package com.oven.service;

import com.oven.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @CacheEvict(value = "FIVE", key = "#id")
    public void delete(Integer id) {
        log.info("删除key为[{}]的缓存", id);
    }

    @Cacheable(value = "FIVE", key = "#id", sync = true)
    public User getById(Integer id) {
        log.info("操作数据库，进行通过ID查询，ID: {}", id);
        return new User(id, "admin", "123", 18);
    }

}
