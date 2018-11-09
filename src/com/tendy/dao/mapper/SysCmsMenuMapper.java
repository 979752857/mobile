package com.tendy.dao.mapper;

import com.tendy.dao.bean.SysCmsMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysCmsMenuMapper {
    int deleteByPrimaryKey(Integer sysMenuId);

    int insertSelective(SysCmsMenu record);

    SysCmsMenu selectByPrimaryKey(Integer sysMenuId);

    int updateByPrimaryKeySelective(SysCmsMenu record);

    List<SysCmsMenu> getMenuList(List<Integer> menuIdList);

    List<SysCmsMenu> getMenuListBySelective(@Param("level")Integer level, @Param("pid")String pid);

}