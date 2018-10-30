package com.sysm.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author : yangxh
 * @create 2018/5/18 18:44
 * @param 
 * @return 
 * @description : 
 **/
/**
 * Security 安全校验实体-用于封转登陆用户的身份信息和权限信息
 * 实质上是对 org.springframework.security.core.userdetails.UserDetails 接口的实现
 * org.springframework.security.core.userdetails.User 提供了构造方法，便于我们业务用户实体和Security 校验身份的实体分离
 * @author Administrator
 *
 */
public class SecurityUser extends User {
    private String uid;
    private String salt;
    private String email;

    public SecurityUser(String uid, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String salt, String email) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.salt = salt;
        this.uid=uid;
        this.email = email;
    }


    public String getSalt() {
        return salt;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
