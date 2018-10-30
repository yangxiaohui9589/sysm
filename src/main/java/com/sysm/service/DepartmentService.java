package com.sysm.service;

import com.sysm.entity.Department;
import com.sysm.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-18 13:21
 * @Team : 系统集成部
 * @description :
 **/
@Service
@Transactional
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }

}
