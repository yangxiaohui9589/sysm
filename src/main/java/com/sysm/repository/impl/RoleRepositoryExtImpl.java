package com.sysm.repository.impl;

import com.sysm.entity.Role;
import com.sysm.repository.RoleRepository;
import com.sysm.repository.RoleRepositoryExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-21 11:59
 * @Team : 系统集成部
 * @description :
 **/
@Component
public class RoleRepositoryExtImpl implements RoleRepositoryExt {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles(Long userId) {
        String sql ="SELECT\n" +
                    "  R.*\n" +
                    "FROM T_ROLE R\n" +
                    "  LEFT JOIN T_USER_ROLE UR\n" +
                    "    ON R.id = UR.role_id\n" +
                    "WHERE UR.user_id = '"+userId+"'";
        Query query = entityManager.createNativeQuery(sql, Role.class);
        List resultList = query.getResultList();
        return resultList;
    }
}
