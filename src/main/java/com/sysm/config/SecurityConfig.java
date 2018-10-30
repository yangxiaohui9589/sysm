package com.sysm.config;

import com.sysm.service.UserService;
import com.sysm.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : yangxh
 * @create 2018/5/19 14:28
 * @param 
 * @return 
 * @description : 
 **/
@Configuration
@EnableWebSecurity //注释掉可以既能享受到springboot的自动配置又能覆盖某些配置
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected UserService userService;

    /**
     * 用户登陆校验
     * 调用了customUserService()，内部覆盖重写了 UserDetailsService.loadUserByUsername,需返回 配置了权限的UserDetails的子类对象
     * 作为登陆用户权限配置的依据
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**对于数据库密码加密的情况*/
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
            //rawPassword 前台传递来的 password
            //encodedPassword 后台计算的 password
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
            }
        });

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bootstrap/**","/dist/**","/js/**","/plugins/**");
    }

    /**
     * 配置 特殊权限-特殊路径
     * 配置 任意权限-剩余路径
     * 配置 登陆页-用户名、密码-登陆失败页-不需要权限
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/druid/*").permitAll()
                .antMatchers("/common/**").hasRole("COMMON")//需要权限ROLE_COMMON 才可以访问的路径
                .anyRequest().authenticated() // 只有具有任意的某个权限就可以访问其他访问-没有权限还是无法访问的
                .and()
                .formLogin()//对于form表单登陆
                .passwordParameter("password").usernameParameter("username")//如果你前台登陆的form表单登录名和密码不是username,password，那么就配置本行修改你需要的名字
                .loginProcessingUrl("/login")
                .loginPage("/to-login")
                .defaultSuccessUrl("/").successHandler(new AuthenticationSuccessHandler())//未登陆的情况下，默认跳转的页面
                .failureUrl("/login?error").permitAll() //如果登陆失败，跳转的url
                .and().logout().logoutSuccessHandler(new LogoutSuccessHandler()).permitAll();// 允许任何请求（不管有没有权限以及拥有何种权限）登出

    }

    private class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authentication)
                throws ServletException, IOException {

            clearAuthenticationAttributes(request);
            if (!isAjax(request)) {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }
    }
    protected boolean isAjax(HttpServletRequest request) {
        return StringUtils.isNotBlank(request.getHeader("X-Requested-With"));
    }

    private class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(HttpServletRequest request,
                                    HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {
            if (!isAjax(request)) {
                super.onLogoutSuccess(request, response, authentication);
            }
        }
    }
}
