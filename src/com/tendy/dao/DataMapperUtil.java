package com.tendy.dao;

import com.tendy.dao.bean.AccountPhone;
import com.tendy.dao.bean.MobileBussiness;
import com.tendy.dao.bean.SysCmsMenu;
import com.tendy.dao.bean.SysUserRole;
import com.tendy.dao.bean.UserAccountPhone;
import com.tendy.dao.mapper.AccountPhoneMapper;
import com.tendy.dao.mapper.MobileBussinessMapper;
import com.tendy.dao.mapper.SysCmsMenuMapper;
import com.tendy.dao.mapper.SysCmsRoleMenuMapper;
import com.tendy.dao.mapper.SysUserRoleMapper;
import com.tendy.dao.mapper.UserAccountPhoneMapper;
import com.tendy.utils.SpringUtils;

import java.util.List;

/**
 * @Author: tendy
 * @Description:
 * @Date: 2018/10/29
 */
public class DataMapperUtil {

    private static MobileBussinessMapper mobileBussinessMapper = SpringUtils.getBean(MobileBussinessMapper.class);
    private static UserAccountPhoneMapper userAccountPhoneMapper = SpringUtils.getBean(UserAccountPhoneMapper.class);
    private static SysCmsMenuMapper sysCmsMenuMapper = SpringUtils.getBean(SysCmsMenuMapper.class);
    private static SysUserRoleMapper sysUserRoleMapper = SpringUtils.getBean(SysUserRoleMapper.class);
    private static SysCmsRoleMenuMapper sysCmsRoleMenuMapper = SpringUtils.getBean(SysCmsRoleMenuMapper.class);

    public static MobileBussiness selectMobileBussinessByPrimaryKey(Integer id){
        return mobileBussinessMapper.selectByPrimaryKey(id);
    }

    public static MobileBussiness selectMobileBussinessByCid(String cid){
        return mobileBussinessMapper.selectByCid(cid);
    }

    public static MobileBussiness selectMobileBussinessByName(String name){
        return mobileBussinessMapper.selectByName(name);
    }

    public static UserAccountPhone selectUserAccountPhoneByPrimaryKey(Long id){
        return userAccountPhoneMapper.selectByPrimaryKey(id);
    }

    public static UserAccountPhone selectUserAccountPhoneByPhone(String phone){
        return userAccountPhoneMapper.selectByPhone(phone);
    }

    public static int updateUserAccountPhoneByPrimaryKeySelective(UserAccountPhone accountPhone){
        return userAccountPhoneMapper.updateByPrimaryKeySelective(accountPhone);
    }

    public static List<UserAccountPhone> selectUserAccountPhoneByPhoneAndBusiness(String phone, Integer businessId,
                                                                                  Integer iDisplayStart, Integer iDisplayLength, String status){
        return userAccountPhoneMapper.selectListByPhone(phone, businessId, iDisplayStart, iDisplayLength, status);
    }

    public static int countUserAccountPhoneByPhoneAndCity(String phone, Integer businessId, String status){
        return userAccountPhoneMapper.countListByPhone(phone, businessId, status);
    }

    public static int insertUserAccountPhoneSelective(UserAccountPhone userAccountPhone){
        return userAccountPhoneMapper.insertSelective(userAccountPhone);
    }

    public static Integer getSysUserRoleRoleIdByBusinessId(Integer businessId){
        return sysUserRoleMapper.getRoleIdByBusinessId(businessId);
    }

    public static List<Integer> getSysCmsRoleMenuMenuIdListByRoleId(Integer sys_role_id) {
        return sysCmsRoleMenuMapper.getMenuIdListByRoleId(sys_role_id);
    }

    public static List<SysCmsMenu> getSysCmsMenuMenuList(List<Integer> menuIdList) {
        return sysCmsMenuMapper.getMenuList(menuIdList);
    }

}