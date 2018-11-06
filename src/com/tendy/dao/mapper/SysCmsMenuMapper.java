package com.tendy.dao.mapper;

import com.tendy.dao.bean.SysCmsMenu;

import java.util.List;

public interface SysCmsMenuMapper {
    int deleteByPrimaryKey(Integer sysMenuId);

    int insertSelective(SysCmsMenu record);

    SysCmsMenu selectByPrimaryKey(Integer sysMenuId);

    int updateByPrimaryKeySelective(SysCmsMenu record);

    List<SysCmsMenu> getMenuList(List<Integer> menuIdList);

}