package com.sysm.resource;

import com.sysm.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-21 16:24
 * @Team : 系统集成部
 * @description :
 **/
@Controller
public class LoginController {

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/edit/{id}")
    public ModelAndView update(ModelMap model, @PathVariable Long id){

        return new ModelAndView("");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(User user,HttpServletRequest request){
        return "";
    }
}
