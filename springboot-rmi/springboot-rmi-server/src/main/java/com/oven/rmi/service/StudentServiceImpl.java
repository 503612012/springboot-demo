package com.oven.rmi.service;

import com.oven.rmi.common.entity.Student;
import com.oven.rmi.common.service.StudentService;
import com.oven.rmi.dao.StudentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public Student getById(Integer id) {
        return studentDao.getById(id);
    }

}
