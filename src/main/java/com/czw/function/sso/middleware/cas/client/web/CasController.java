package com.czw.function.sso.middleware.cas.client.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class CasController {

    @RequestMapping("/")
    String index() {
        return "访问了首页哦";
    }

    @RequestMapping("/hello")
    String hello() {
        return "不验证哦";
    }

    @PreAuthorize("hasAuthority('TEST')")//有TEST权限的才能访问
    @RequestMapping("/security")
    String security() {
        return "hello world security";
    }

    @PreAuthorize("hasAuthority('ADMIN')")//必须要有ADMIN权限的才能访问  
    @RequestMapping("/authorize")
    String authorize() {
        return "有权限访问";
    }
}