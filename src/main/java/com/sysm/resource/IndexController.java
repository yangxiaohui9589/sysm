package com.sysm.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-21 16:21
 * @Team : 系统集成部
 * @description :
 **/
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
