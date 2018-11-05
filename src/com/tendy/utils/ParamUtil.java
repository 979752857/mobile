package com.tendy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamUtil {

    public static boolean checkParamIsNull(String... params){
        for (String param : params){
            if(StringUtils.isBlank(param)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkObjIsNull(Object... params){
        for (Object param : params){
            if(param == null){
                return true;
            }
        }
        return false;
    }

    /**
     * 检验手机号是否非法
     * @param phone
     * @return true:非法 false:合法
     */
    public static boolean checkPhoneIllegal(String phone){
        String reg = "^[1][3,4,5,6,7,8,9][0-9]{9}$";
        Pattern pattern  = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(phone);
        return !matcher.matches();
    }

    /**
     * 检查参数是否为空
     * @param params
     * @return true: 空  false: 非空
     */
    public static boolean checkCityIsNull(String... params){
        for(String param : params) {
            if(StringUtils.isBlank(param)) {
                return true;
            }else{
                if("null".equals(param)){
                    return true;
                }
                if("undefined".equals(param)){
                    return true;
                }
            }
        }
        return false;
    }

}
