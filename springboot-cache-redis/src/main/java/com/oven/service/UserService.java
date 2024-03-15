package com.oven.service;

import com.oven.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    @Cacheable
    public User getById(Integer id) {
        log.info("查询数据库了。。。");
        return new User(id, "admin", "123", 18);
    }

    @CacheEvict
    public void delete(Integer id) {
        log.info("删除[{}]缓存", id);
    }

}
