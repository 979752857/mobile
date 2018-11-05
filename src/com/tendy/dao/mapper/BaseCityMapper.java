package com.tendy.dao.mapper;

import com.tendy.dao.bean.BaseCity;

public interface BaseCityMapper {

    int insertSelective(BaseCity record);

    BaseCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseCity record);
}