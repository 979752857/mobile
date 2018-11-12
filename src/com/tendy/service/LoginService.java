package com.tendy.service;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.MobileBussiness;
import com.tendy.dao.bean.SysCmsMenu;
import com.tendy.model.MenuModel;
import com.tendy.utils.EncryptUtil;
import com.tendy.utils.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService extends BaseService{

    /**
     * 返回运营商实体
     * @param name
     * @param password
     * @return
     */
    public ReplyMap checkLogin(String name, String password, HttpSession httpSession) {
        ReplyMap replyMap = new ReplyMap();

        MobileBussiness mobileBussiness = DataMapperUtil.selectMobileBussinessByName(name);
        if(mobileBussiness == null){
            replyMap.fail(BusinessConstants.USER_NULL_CODE, BusinessConstants.USER_NULL_MSG);
            return replyMap;
        }
        //判断密码
        password = EncryptUtil.MD5(name + password);
        if(!password.equals(mobileBussiness.getPassword())){
            replyMap.fail(BusinessConstants.PASSWORD_ERROR_CODE, BusinessConstants.PASSWORD_ERROR_MSG);
            return replyMap;
        }
        //判断运营商有效期
        if(mobileBussiness.getEndTime().before(new Date())){
            replyMap.fail(BusinessConstants.VALID_TIMEOUT_CODE, BusinessConstants.VALID_TIMEOUT_MSG);
            return replyMap;
        }
        //放入session数据
        httpSession.setAttribute("id", mobileBussiness.getId());
        httpSession.setAttribute("name", mobileBussiness.getName());
        httpSession.setAttribute("cid", mobileBussiness.getCid());
        httpSession.setAttribute("city", mobileBussiness.getCityId());
        httpSession.setAttribute("businessName", mobileBussiness.getBussinessName());
        httpSession.setAttribute("endTime", TimeUtil.formatDate(mobileBussiness.getEndTime(), TimeUtil.YYYY_MM_DD));
        // 存储可以访问的菜单
        List<SysCmsMenu> menuList = getMenuList(mobileBussiness.getId());
        httpSession.setAttribute("menuList", menuList);
        logger.info("登录成功  bussinessId:{}  name:{}   businessName:{}", mobileBussiness.getId(), mobileBussiness.getName(), mobileBussiness.getBussinessName());
        replyMap.success();
        return replyMap;
    }

    public List<SysCmsMenu> getMenuList(Integer userId) {
        List<SysCmsMenu> menuList = new ArrayList<SysCmsMenu>();
        Integer sys_role_id = DataMapperUtil.getSysUserRoleRoleIdByBusinessId(userId);
        if (null!=sys_role_id) {
            List<Integer> menuIdList = DataMapperUtil.getSysCmsRoleMenuMenuIdListByRoleId(sys_role_id);
            if(!CollectionUtils.isEmpty(menuIdList)){
                menuList = DataMapperUtil.getSysCmsMenuMenuList(menuIdList);
            }
        }
        return menuList;
    }
    /**
     * 解析session中获取到的菜单,转换为可递归调用的List数据
     * @param menuList
     * @return
     */
    public List<MenuModel> getLevelMenuModeList(List<SysCmsMenu> menuList){
        List<MenuModel> menuModels = new ArrayList<>();
        Map<String, Map<String, MenuModel>> levelMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(menuList)){
            //level一级菜单为1
            int maxLevel = 1;
            //将菜单分level放入levelMap中
            for(SysCmsMenu menu : menuList){
                if(maxLevel < menu.getLevel()){
                    maxLevel = menu.getLevel();
                }
                Map<String, MenuModel> menuMap = levelMap.get(String.valueOf(menu.getLevel()));
                if(menuMap == null){
                    menuMap = new HashMap<>();
                }
                menuMap.put(String.valueOf(menu.getSysMenuId()), sysCmsMenuToMenuModel(menu));
                levelMap.put(String.valueOf(menu.getLevel()), menuMap);
            }
            //从最大level开始逐层遍历map放入上一层中
            for(int i = maxLevel; i > 1; i--){
                Map<String, MenuModel> curLevelMap = levelMap.get(String.valueOf(i));
                Map<String, MenuModel> upLevelMap = levelMap.get(String.valueOf(i-1));
                for (Map.Entry<String, MenuModel> entry : curLevelMap.entrySet()) {
                    MenuModel model = entry.getValue();
                    if(upLevelMap.get(String.valueOf(model.getMenuPid())) != null){
                        MenuModel pModel = upLevelMap.get(String.valueOf(model.getMenuPid()));
                        pModel.getList().add(model);
                        upLevelMap.put(String.valueOf(model.getMenuPid()), pModel);
                    }
                }
                levelMap.put(String.valueOf(i), upLevelMap);
            }
            //遍历leve为1的主菜单放入list中
            for (Map.Entry<String, MenuModel> entry : levelMap.get("1").entrySet()) {
                MenuModel model = entry.getValue();
                menuModels.add(model);
            }
        }
        return menuModels;
    }

    private MenuModel sysCmsMenuToMenuModel(SysCmsMenu menu){

        MenuModel menuModel = new MenuModel();
        menuModel.setIsfunction(menu.getIsfunction());
        menuModel.setLevel(menu.getLevel());
        menuModel.setList(new ArrayList<MenuModel>());
        menuModel.setMenuUrl(menu.getMenuUrl());
        menuModel.setMenuIconUrl(menu.getMenuIconUrl());
        menuModel.setMenuName(menu.getMenuName());
        menuModel.setMenuPid(menu.getMenuPid());
        menuModel.setSequence(menu.getSequence());
        menuModel.setSysMenuId(menu.getSysMenuId());
        return menuModel;
    }
}