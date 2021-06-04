package com.zr.domain.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AuthToken {

    //访问token就是短令牌，用户身份令牌
    private String jtl;

    //刷新token
    private String refreshToken;

    //jwt令牌(长令牌，真实令牌)
    private String jwt;

}
