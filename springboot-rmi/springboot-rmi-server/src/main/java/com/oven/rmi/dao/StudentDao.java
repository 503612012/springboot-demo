package com.oven.rmi.dao;

import com.oven.rmi.common.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    public Student getById(Integer id) {
        Student student = new Student();
        student.setId(id);
        student.setName("Oven");
        student.setAge(18);
        student.setPhone("15700000001");
        return student;
    }

}
