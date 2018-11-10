package com.tendy.controller;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.service.LoginService;
import com.tendy.utils.ParamUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    @ResponseBody
    public String checkLogin(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession httpSession) {
        ReplyMap replyMap = new ReplyMap();
        if(ParamUtil.checkParamIsNull(name, password)){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
            return replyMap.toJson();
        }
        if(password.length() < 6){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "密码至少6位以上");
            return replyMap.toJson();
        }
        replyMap = loginService.checkLogin(name, password, httpSession);
        return replyMap.toJson();
    }
}