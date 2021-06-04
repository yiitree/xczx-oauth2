package com.zr.auth.dao;


import com.zr.domain.XcMenu;
import org.mapstruct.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 */
@Mapper
@Resource
public interface XcMenuMapper {
    //根据用户id查询用户的权限
    List<XcMenu> selectPermissionByUserId(String userid);
}
