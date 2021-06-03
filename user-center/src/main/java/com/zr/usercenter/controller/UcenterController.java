package com.zr.usercenter.controller;

import com.zr.usercenter.domain.ext.XcUserExt;
import com.zr.usercenter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@Api(value = "用户中心",description = "用户中心管理")
@RestController
@RequestMapping("/ucenter")
public class UcenterController {
    @Autowired
    UserService userService;

    /**
     * 通过用户名查询用户信息 所有信息 + 权限信息
     * @param username
     * @return
     */
    @ApiOperation("根据用户账号查询用户信息")
    @GetMapping("/getuserext")
    public XcUserExt getUserext(@RequestParam("username") String username) {
        return userService.getUserExt(username);
    }

}
