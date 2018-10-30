package com.sysm.repository;

import com.sysm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-18 10:07
 * @Team : 系统集成部
 * @description :
 **/
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByusername(String username);
}

