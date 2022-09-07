package com.oven.service;

import com.oven.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(User user) {
        mongoTemplate.insert(user, "t_user");
    }

    public User findById(String userName, String tname) {
        Criteria criteria = new Criteria();
        criteria.and("userName").is(userName);
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, User.class, tname);
    }

}
