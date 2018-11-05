package com.tendy.service;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.MobileBussiness;
import com.tendy.utils.EncryptUtil;
import com.tendy.utils.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;

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
        httpSession.setAttribute("businessName", mobileBussiness.getBussinessName());
        httpSession.setAttribute("endTime", TimeUtil.formatDate(mobileBussiness.getEndTime(), TimeUtil.YYYY_MM_DD));
        logger.info("登录成功  bussinessId:{}  name:{}   businessName:{}", mobileBussiness.getId(), mobileBussiness.getName(), mobileBussiness.getBussinessName());
        replyMap.success();
        return replyMap;
    }
}