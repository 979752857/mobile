package com.tendy.dao.mapper;

import com.tendy.dao.bean.UserAccountPhone;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAccountPhoneMapper {

    int insertSelective(UserAccountPhone record);

    UserAccountPhone selectByPrimaryKey(Long id);

    UserAccountPhone selectByPhone(String phone);

    List<UserAccountPhone> selectListByPhone(@Param("phone")String phone, @Param("businessId")Integer businessId,
                                             @Param("iDisplayStart")Integer iDisplayStart, @Param("iDisplayLength")Integer iDisplayLength,
                                             @Param("status")String status, @Param("tag")String tag, @Param("notPhone")String notPhone,
                                             @Param("openBusinessId")Integer openBusinessId, @Param("position")String position);

    UserAccountPhone selectByPhoneAndBusId(@Param("phone")String phone, @Param("businessId")Integer businessId);

    int countListByPhone(@Param("phone")String phone, @Param("businessId")Integer businessId, @Param("status")String status,
                         @Param("tag")String tag, @Param("notPhone")String notPhone, @Param("openBusinessId")Integer openBusinessId, @Param("position")String position);

    int updateByPrimaryKeySelective(UserAccountPhone record);

}