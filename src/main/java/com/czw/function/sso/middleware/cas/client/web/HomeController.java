package com.czw.function.sso.middleware.cas.client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author ZeviChen , 2017/6/8 11:11
 */
//@RestController
@Controller
@RequestMapping("/index")
public class HomeController {

    @RequestMapping("/")
    String home(HttpSession session, Model model) {
        System.out.println("进入首页控制器");

        model.addAttribute("msg", "msg");
        model.addAttribute("jsessionid", session.getId());
        return "index";
    }

}
