package com.tendy.controller;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.service.LoginService;
import com.tendy.service.UserInfoService;
import com.tendy.utils.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public String updateInfo(@RequestParam("name") String name, @RequestParam("password") String password,
                             @RequestParam("phone") String phone, @RequestParam("address") String address,
                             @RequestParam("remark") String remark, @RequestParam("busName") String busName,
                             HttpSession httpSession) {
        ReplyMap replyMap = new ReplyMap();
        if(ParamUtil.checkParamIsNull(name, phone)){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
            return replyMap.toJson();
        }
        Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
        replyMap = userInfoService.updateUserInfo(businessId, name, password, phone, address, remark, busName);
        return replyMap.toJson();
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    @ResponseBody
    public String userInfo(HttpSession httpSession) {
        ReplyMap replyMap = new ReplyMap();
        Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
        try {
            replyMap = userInfoService.getUserInfo(businessId);
        } catch (ParseException e) {
            logger.error("UserInfoController userInfo is error businessId:{}", businessId, e);
            replyMap.fail(BusinessConstants.SERVER_ERROR_CODE, BusinessConstants.SERVER_ERROR_MSG);
        }
        return replyMap.toJson();
    }
}