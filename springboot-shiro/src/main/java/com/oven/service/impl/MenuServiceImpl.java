package com.oven.sevice.impl;

import com.oven.dao.MenuDao;
import com.oven.entity.Menu;
import com.oven.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    @Override
    public Menu findById(Integer id) {
        return menuDao.findById(id);
    }

}

