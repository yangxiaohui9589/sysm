package com.sysm.service;

import com.sysm.entity.Role;
import com.sysm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-18 13:22
 * @Team : 系统集成部
 * @description :
 **/
@Service
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
}
