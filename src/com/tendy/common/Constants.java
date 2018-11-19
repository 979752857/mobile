package com.tendy.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tendy
 * @Description:
 * @Date: 2018/10/29
 */
public class Constants {

    public static String OPEN_BUSINESS_KEY = "open_businessid";
    public static String OPEN_BUSINESSID_PRICE_KEY = "open_businessid_price";
    public static String GEOMANTIC_KEY = "geomantic";

    public static String getPhoneType(String type){
        return phoneTypeMap.get(type);
    }

    public static Map<String, String> phoneTypeMap = new HashMap<>();
    static{
        phoneTypeMap.put("Mobile", "移动");
        phoneTypeMap.put("Unicom", "联通");
        phoneTypeMap.put("Telecom", "电信");
    }
}