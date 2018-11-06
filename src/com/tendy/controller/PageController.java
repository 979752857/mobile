package com.tendy.controller;

import com.tendy.dao.bean.SysCmsMenu;
import com.tendy.model.MenuModel;
import com.tendy.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page")
public class PageController extends BaseController {

    private Logger logger = Logger.getLogger(PageController.class);

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = {"/toIndex"}, method = {RequestMethod.GET})
    public ModelAndView toIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/toLogin"}, method = {RequestMethod.GET})
    public ModelAndView toLogin() {
        logger.info("用户登录");
        return new ModelAndView("login");
    }

    @RequestMapping(value = {"/toHomePage"}, method = {RequestMethod.GET})
    public ModelAndView toHomePage(HttpSession httpSession) {
        //从session中获取用户菜单列表
        List<SysCmsMenu> menuList = (List<SysCmsMenu>) httpSession.getAttribute("menuList");
        List<MenuModel> menuModels = loginService.getLevelMenuModeList(menuList);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("menuList", menuModels);
        return new ModelAndView("homepage", resultMap);
    }

    @RequestMapping(value = {"/toOpTable"}, method = {RequestMethod.GET})
    public ModelAndView toOpTable() {
        return new ModelAndView("optable");
    }

    @RequestMapping(value = {"/toPhoneListDetail"}, method = {RequestMethod.GET})
    public ModelAndView toPhoneListDetail() {
        return new ModelAndView("phoneListDetail");
    }
}
