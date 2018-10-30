package com.sysm.repository;

import com.sysm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-18 10:07
 * @Team : 系统集成部
 * @description :
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role,Long>,RoleRepositoryExt{
}
