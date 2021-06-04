package com.zr.domain.request;

import com.zr.model.request.RequestData;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by admin on 2018/3/5.
 */
@Data
public class LoginRequest extends RequestData {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @NotNull
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @NotNull
    private String password;

    /**
     * 验证码
     */
    String verifycode;

}
