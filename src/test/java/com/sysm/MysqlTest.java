//package com.sysm;
//
//import com.sysm.entity.Department;
//import com.sysm.entity.Role;
//import com.sysm.entity.User;
//import com.sysm.repository.DepartmentRepository;
//import com.sysm.repository.RoleRepository;
//import com.sysm.repository.UserRepository;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author : yangxh
// * @version : 1.0
// * @create 2018-05-18 10:45
// * @Team : 系统集成部
// * @description :
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MysqlTest {
//    private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);
//
//    @Autowired
//    DepartmentRepository departmentRepository;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Before
//    public void initData(){
//
//        //清空数据
//        userRepository.deleteAll();
//        departmentRepository.deleteAll();
//        roleRepository.deleteAll();
//
//        //添加部门信息
//        Department department = new Department();
//        department.setName("系统集成部");
//        departmentRepository.save(department);
//        Assert.assertNotNull(department.getId());
//        //添加角色信息
//        Role role = new Role();
//        role.setName("项目经理");
//        roleRepository.save(role);
//        Assert.assertNotNull(role.getId());
//        //添加用户信息
//        User user = new User();
////        user.setName("张磊");
////        user.setCreatedate(new Date());
//        user.setDepartment(department);
//        Set<Role> roleSet = new HashSet<Role>();
//        List<Role> roleList = roleRepository.findAll();
//        for(Role ro:roleList){
//            roleSet.add(ro);
//        }
//        Assert.assertNotNull(roleSet);
//        user.setRoleSet(roleSet);
//        userRepository.save(user);
//        Assert.assertNotNull(user.getId());
//    }
//    @Test
//    public void findPage(){
//        Pageable pageable = new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
//        Page<User> page = userRepository.findAll(pageable);
//        Assert.assertNotNull(page);
//        for(User user:page){
//            logger.info("用户名称，部门名称，角色名称",user.getName(),user.getDepartment().getName(),user.getRoleSet().iterator().next().getName());
//        }
//    }
//}
