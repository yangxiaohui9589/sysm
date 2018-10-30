//package com.sysm;
//
//import com.sysm.entity.Department;
//import com.sysm.entity.Role;
//import com.sysm.entity.User;
//import com.sysm.redis.UserRdis;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @author : yangxh
// * @version : 1.0
// * @create 2018-05-18 14:58
// * @Team : 系统集成部
// * @description :
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RedisTest {
//    private Logger logger = LoggerFactory.getLogger(RedisTest.class);
//
//    @Autowired
//    UserRdis userRdis;
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @Before
//    public void setUp(){
//        //添加部门信息
//        Department department = new Department();
//        department.setName("系统集成部");
//        //添加角色信息
//        Role role = new Role();
//        role.setName("项目经理");
//        //添加用户信息
//        User user = new User();
//        user.setName("user");
//        user.setCreatedate(new Date());
//        user.setDepartment(department);
//        Set<Role> roleSet = new HashSet<Role>();
//        roleSet.add(role);
//        user.setRoleSet(roleSet);
//        //redisTemplate.opsForValue().set("name","hello world!",10, TimeUnit.DAYS);
//        //userRdis.delete(this.getClass().getName()+":userByName:"+user.getName());
//        //System.out.print("存储的name的值1："+redisTemplate.opsForValue().get("name"));
//        //redisTemplate.opsForValue().getOperations().delete("name");
//        //System.out.print("存储的name的值2："+redisTemplate.opsForValue().get("name"));
//        userRdis.add(this.getClass().getName()+":userByName:"+user.getName(),10000L,user);
//
//    }
//    @Test
//    public void get(){
//        User user = userRdis.get(this.getClass().getName()+":userByName:user");
//        Assert.assertNotNull(user);
//        logger.info("用户名称，部门名称，角色名称",user.getName(),user.getDepartment().getName(),user.getRoleSet().iterator().next().getName());
//    }
//
//
//
//}
