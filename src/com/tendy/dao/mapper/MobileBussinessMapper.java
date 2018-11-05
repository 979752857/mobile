package com.tendy.dao.mapper;

import com.tendy.dao.bean.MobileBussiness;
import org.apache.ibatis.annotations.Param;

public interface MobileBussinessMapper {

    int insertSelective(MobileBussiness record);

    MobileBussiness selectByPrimaryKey(Integer id);

    MobileBussiness selectByName(@Param("name") String name);

    MobileBussiness selectByCid(@Param("cid") String cid);

    int updateByPrimaryKeySelective(MobileBussiness record);

}