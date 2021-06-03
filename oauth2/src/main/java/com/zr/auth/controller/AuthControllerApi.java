package com.zr.auth.controller;

import com.zr.domain.request.LoginRequest;
import com.zr.domain.response.JwtResult;
import com.zr.domain.response.LoginResult;
import com.zr.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator.
 */
@Api(value = "用户认证",description = "用户认证接口")
public interface AuthControllerApi {
    @ApiOperation("登录")
    LoginResult login(LoginRequest loginRequest);

    @ApiOperation("退出")
    ResponseResult logout();

    @ApiOperation("查询用户jwt令牌")
    JwtResult userjwt();
}
