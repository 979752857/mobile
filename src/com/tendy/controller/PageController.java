package com.tendy.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page")
public class PageController extends BaseController {

    private Logger logger = Logger.getLogger(PageController.class);

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
    public ModelAndView toHomePage() {
        return new ModelAndView("homepage");
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
