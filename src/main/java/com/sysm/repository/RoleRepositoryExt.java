package com.sysm.repository;

import com.sysm.entity.Role;

import java.util.List;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-21 13:48
 * @Team : 系统集成部
 * @description :
 **/
public interface RoleRepositoryExt {
    List<Role> getRoles(Long userId);
}
