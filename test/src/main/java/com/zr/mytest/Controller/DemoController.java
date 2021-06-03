package com.zr.mytest.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 曾睿
 * @Date: 2021/6/3 20:53
 */
@RestController
public class DemoController {

    @GetMapping("/test/t")
    public String test(){
        return "OK";
    }

    //当用户拥有course_teachplan_list权限时候方可访问此方法
    @PreAuthorize("hasAuthority('test')")
    @GetMapping("/test/t1")
    public String test1(){
        return "OK1";
    }

}
