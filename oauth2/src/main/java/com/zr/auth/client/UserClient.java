package com.zr.auth.client;

import com.zr.client.XcServiceList;
import com.zr.domain.ext.XcUserExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = XcServiceList.XC_SERVICE_UCENTER)
public interface UserClient {
    //根据账号查询用户信息
    @GetMapping("/ucenter/getuserext")
    XcUserExt getUserext(@RequestParam("username") String username);
}
