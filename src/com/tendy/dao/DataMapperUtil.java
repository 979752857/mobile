package com.tendy.dao;

import com.tendy.dao.bean.*;
import com.tendy.dao.mapper.*;
import com.tendy.utils.SpringUtils;

import java.util.List;

public class DataMapperUtil {

    private static MobileBussinessMapper mobileBussinessMapper = SpringUtils.getBean(MobileBussinessMapper.class);
    private static UserAccountPhoneMapper userAccountPhoneMapper = SpringUtils.getBean(UserAccountPhoneMapper.class);
    private static SysCmsMenuMapper sysCmsMenuMapper = SpringUtils.getBean(SysCmsMenuMapper.class);
    private static SysUserRoleMapper sysUserRoleMapper = SpringUtils.getBean(SysUserRoleMapper.class);
    private static SysCmsRoleMenuMapper sysCmsRoleMenuMapper = SpringUtils.getBean(SysCmsRoleMenuMapper.class);
    private static BaseCityMapper baseCityMapper = SpringUtils.getBean(BaseCityMapper.class);
    private static PhoneAlertConfigMapper phoneAlertConfigMapper = SpringUtils.getBean(PhoneAlertConfigMapper.class);

    public static MobileBussiness selectMobileBussinessByPrimaryKey(Integer id){
        return mobileBussinessMapper.selectByPrimaryKey(id);
    }

    public static MobileBussiness selectMobileBussinessByCid(String cid){
        return mobileBussinessMapper.selectByCid(cid);
    }

    public static MobileBussiness selectMobileBussinessByName(String name){
        return mobileBussinessMapper.selectByName(name);
    }

    public static int updateMobileBussinessByPrimaryKeySelective(MobileBussiness mobileBussiness){
        return mobileBussinessMapper.updateByPrimaryKeySelective(mobileBussiness);
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

    public static int insertUserAccountPhoneSelectiveBatch(List<UserAccountPhone> accountPhones){
        return userAccountPhoneMapper.insertSelectiveBatch(accountPhones);
    }

    public static int insertUserAccountPhoneSelective(UserAccountPhone accountPhone){
        return userAccountPhoneMapper.insertSelective(accountPhone);
    }

    public static List<PhoneAlertConfig> selectPhoneAlertConfigAllConfig(){
        return phoneAlertConfigMapper.selectAllConfig();
    }

    public static List<UserAccountPhone> selectUserAccountPhoneByPhoneAndBusiness(String phone, Integer businessId, Integer iDisplayStart,
                                                                                  Integer iDisplayLength, String status, String tag, String notPhone, Integer openBusinessId, String position){
        return userAccountPhoneMapper.selectListByPhone(phone, businessId, iDisplayStart, iDisplayLength, status, tag, notPhone, openBusinessId, position);
    }

    public static UserAccountPhone selectUserAccountPhoneByPhoneAndBusId(String phone, Integer businessId){
        return userAccountPhoneMapper.selectByPhoneAndBusId(phone, businessId);
    }

    public static int countUserAccountPhoneByPhoneAndCity(String phone, Integer businessId, String status, String tag, String notPhone, Integer openBusinessId, String position){
        return userAccountPhoneMapper.countListByPhone(phone, businessId, status, tag, notPhone, openBusinessId, position);
    }

    public static int insertOrUpdateUserAccountPhoneSelective(UserAccountPhone userAccountPhone){
        if(userAccountPhone.getId() != null && userAccountPhone.getId() > 0){
            return userAccountPhoneMapper.updateByPrimaryKeySelective(userAccountPhone);
        }
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

    public static List<SysCmsMenu> getSysCmsMenuMenuListBySelective(Integer level, String pid) {
        return sysCmsMenuMapper.getMenuListBySelective(level, pid);
    }

    public static BaseCity selectBaseCityByPrimaryKey(Integer cityId) {
        return baseCityMapper.selectByPrimaryKey(cityId);
    }

}