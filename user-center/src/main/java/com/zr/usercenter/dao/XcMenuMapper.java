package com.zr.usercenter.dao;

import com.zr.usercenter.domain.XcMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator.
 */
@Mapper
public interface XcMenuMapper {
    //根据用户id查询用户的权限
    List<XcMenu> selectPermissionByUserId(String userid);
}
