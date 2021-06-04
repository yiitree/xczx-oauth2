package com.zr.auth.service;

import com.zr.auth.dao.XcMenuMapper;
import com.zr.auth.dao.XcUserRepository;
import com.zr.domain.XcMenu;
import com.zr.domain.XcUser;
import com.zr.domain.ext.XcUserExt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    XcUserRepository xcUserRepository;
    @Autowired
    XcMenuMapper xcMenuMapper;

    /**
     * 根据账号查询用户信息
     * 权限信息
     * @param username
     * @return
     */
    public XcUserExt getUserExt(String username){
        //根据账号查询xcUser信息
        XcUser xcUser = xcUserRepository.findByUsername(username);
        if(xcUser == null){
            return null;
        }
        //用户id
        String userId = xcUser.getId();
        //查询用户所有权限
        List<XcMenu> xcMenus = xcMenuMapper.selectPermissionByUserId(userId);
        XcUserExt xcUserExt = new XcUserExt();
        BeanUtils.copyProperties(xcUser,xcUserExt);
        //设置权限
        xcUserExt.setPermissions(xcMenus);
        return xcUserExt;
    }

}
