package com.tendy.dao.mapper;

import com.tendy.dao.bean.UserAccountPhone;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAccountPhoneMapper {

    int insertSelective(UserAccountPhone record);

    UserAccountPhone selectByPrimaryKey(Long id);

    List<UserAccountPhone> selectListByPhone(@Param("phone")String phone, @Param("business_id")Integer business_id,
                                             @Param("iDisplayStart")Integer iDisplayStart, @Param("iDisplayLength")Integer iDisplayLength,
                                             @Param("status")String status);

    int countListByPhone(@Param("phone")String phone, @Param("business_id")Integer business_id, @Param("status")String status);

    int updateByPrimaryKeySelective(UserAccountPhone record);

}