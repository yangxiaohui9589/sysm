package com.sysm.service;

import com.sysm.entity.Role;
import com.sysm.entity.User;
import com.sysm.repository.RoleRepository;
import com.sysm.repository.UserRepository;
import com.sysm.entity.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-18 13:21
 * @Team : 系统集成部
 * @description :
 **/
/**
 * 本类需要实现 org.springframework.security.core.userdetails.UserDetailsService 接口
 * 然后覆盖重写 loadUserByUsername(String userName) 方法
 * 在该方法内部，需要添加 userName,passWord,权限集合,其他参数 到我们已经处理好的com.bestcxx.stu.springbootsecuritydb.security.bean.SecurityUser
 * 然后返回即可-没有密码校验？是的，密码校验不在这个地方,已经被封装好了，但是-数据库密码一般都是加密的，前端信息传递到这还是明文形式，
 * 所以肯定有办法覆盖重写密码校验的方法，这里先卖个关子，先让我们的程序在密码明文校验的情况下顺利运行吧
 * 好吧，就是在 com.bestcxx.stu.springbootsecuritydb.config.WebSecurityConfig 类中配置了怎么校验密码
 *
 * 本例没有连接数据库，但是会按照连接数据库的流程进行讲解
 *
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByusername(username);
        if(user==null){
            throw new UsernameNotFoundException("no user");
        }
        SecurityUser userDetails = new SecurityUser(String.valueOf(user.getId()), username, user.getPassword(), !user.isDisabled(), true, true, true, grantedAuthorities(user.getId()), user.getSalt(), user.getEmail());
        return userDetails;
    }

    protected Collection<GrantedAuthority> grantedAuthorities(Long userId){
        List<Role> resources=roleRepository.getRoles(userId);
        if(CollectionUtils.isEmpty(resources)){
            return new ArrayList<>();
        }
        Collection<GrantedAuthority> authorities=new HashSet<>();
        //忽略已经禁用的角色
        resources.stream().filter(role -> !role.isDisabled()).forEach((resource -> {
            authorities.add(new SimpleGrantedAuthority(resource.getName()));
        }));
        return authorities;
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
