package com.tendy.dao.mapper;

import com.tendy.dao.bean.AccountPhone;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountPhoneMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(AccountPhone record);

    AccountPhone selectByPrimaryKey(Integer id);

    List<AccountPhone> selectListByPhone(@Param("phone")String phone, @Param("city_id")Integer city,
                                         @Param("iDisplayStart")Integer iDisplayStart, @Param("iDisplayLength")Integer iDisplayLength,
                                         @Param("status")String status);

    int countListByPhone(@Param("phone")String phone, @Param("city_id")Integer city, @Param("status")String status);

    int updateByPrimaryKeySelective(AccountPhone record);
}