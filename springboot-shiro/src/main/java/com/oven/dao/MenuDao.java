package com.oven.dao;

import com.oven.entity.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuDao extends CrudRepository<Menu, Long> {

    Menu findById(Integer id);

}