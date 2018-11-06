package com.tendy.dao.mapper;

import com.tendy.dao.bean.SysCmsRoleMenu;

import java.util.List;

public interface SysCmsRoleMenuMapper {

    int insertSelective(SysCmsRoleMenu record);

    SysCmsRoleMenu selectByPrimaryKey(Integer sysRoleMenuId);

    List<Integer> getMenuIdListByRoleId(Integer sys_role_id);

    int updateByPrimaryKeySelective(SysCmsRoleMenu record);

}