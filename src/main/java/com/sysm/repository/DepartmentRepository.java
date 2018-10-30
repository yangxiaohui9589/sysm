package com.sysm.repository;

import com.sysm.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-18 10:03
 * @Team : 系统集成部
 * @description :
 **/
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
