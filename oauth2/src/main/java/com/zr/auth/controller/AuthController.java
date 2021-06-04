package com.zr.auth.controller;

import com.zr.auth.service.AuthService;
import com.zr.domain.ext.AuthToken;
import com.zr.domain.request.LoginRequest;
import com.zr.domain.response.JwtResult;
import com.zr.domain.response.LoginResult;
import com.zr.model.response.CommonCode;
import com.zr.model.response.ResponseResult;
import com.zr.util.CookieUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(value = "用户认证",description = "用户认证接口")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    private AuthService authService;

    /**
     * 用户登录（用户名、密码、验证码）
     * 申请授权码 - 有了授权码 - 去授权中心 - 获得令牌 - 携带令牌访问资源
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public LoginResult login(LoginRequest loginRequest,HttpServletResponse response) {
        //申请令牌
        AuthToken authToken = authService.login(loginRequest.getUsername(),loginRequest.getPassword(),clientId,clientSecret);
        //用户身份令牌
        String jtl = authToken.getJtl();
        //将令牌存储到cookie
        CookieUtil.addCookie(response,cookieDomain,"/","uid",jtl,cookieMaxAge,false);
        return new LoginResult(CommonCode.SUCCESS,jtl);
    }

    @ApiOperation("退出")
    @PostMapping("/logout")
    public ResponseResult logout() {
        //取出cookie中的用户身份令牌
        String uid = getTokenFormCookie();
        //删除redis中的token
        authService.delToken(uid);
        //清除cookie
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtil.addCookie(response,cookieDomain,"/","uid",uid,0,false);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 由于登录功能，返回前端的是jti小令牌，因此前端携带jti（保存在cookie中）访问该接口，
     * 后端从redis中获得用户jwt完整令牌返回
     * 申请令牌
     */
    @ApiOperation("查询用户jwt令牌")
    @GetMapping("/getJwt")
    public JwtResult getJwt() {
        //取出cookie中的用户身份令牌
        String uid = getTokenFormCookie();
        if(uid == null){
            return new JwtResult(CommonCode.FAIL,null);
        }
        //拿身份令牌jti从redis中查询jwt令牌
        AuthToken userToken = authService.getUserToken(uid);
        if(userToken!=null){
            //将jwt令牌返回给用户
            String jwt = userToken.getJwt();
            return new JwtResult(CommonCode.SUCCESS,jwt);
        }
        return null;
    }

    /**
     * 取出cookie中的身份令牌
     */
    private String getTokenFormCookie(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if(map != null && map.get("uid")!=null){
            String uid = map.get("uid");
            return uid;
        }
        return null;
    }

}
