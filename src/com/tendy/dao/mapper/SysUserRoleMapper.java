package com.tendy.dao.mapper;

import com.tendy.dao.bean.SysUserRole;

public interface SysUserRoleMapper {

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer sysUserRoleId);

    Integer getRoleIdByBusinessId(Integer businessId);

    int updateByPrimaryKeySelective(SysUserRole record);

}