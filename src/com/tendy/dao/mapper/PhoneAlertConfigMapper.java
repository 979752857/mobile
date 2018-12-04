package com.tendy.dao.mapper;

import com.tendy.dao.bean.PhoneAlertConfig;

import java.util.List;

public interface PhoneAlertConfigMapper {

    PhoneAlertConfig selectByPrimaryKey(Integer id);

    List<PhoneAlertConfig> selectAllConfig();

}