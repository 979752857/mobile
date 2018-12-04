package com.tendy.spider;

import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.UserAccountPhone;
import com.tendy.utils.HttpConnectionUtil;
import com.tendy.utils.JsonMapper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HenanPhone extends Phone{

    private static String server = "http://wap.ha.10086.cn/pay/card-sale!toforward.action?url=card&mealid=&mastercard=&plancode=&iccid=";

    public HenanPhone(Integer pageSize, Integer pageStart, Integer pageEnd, Integer cityId, Integer businessId) {
        super(pageSize, pageStart, pageEnd, cityId, businessId);
    }

    public void getPhoneAndHref(int row) throws InterruptedException {
        Map<String, String> param = new HashMap<>();
        param.put("queryRegion", "R");
        param.put("sumpage", "0");
        String result = HttpConnectionUtil.requestMethod(HttpConnectionUtil.HTTP_POST, server, HttpConnectionUtil.convertStringParamter(param), null);
        result = result.trim();
        System.out.println(result);
        processHtmlAndHref(result, row);
    }

    public void processHtmlAndHref(String result, int row) throws InterruptedException {
        List<UserAccountPhone> list = new ArrayList<>();
        while(true){
            result = result.trim();
            int index = result.indexOf("class=\"tc bgc-fafafa\"");
            if(index < 0){
                break;
            }
            result = result.substring(index + "class=\"tc bgc-fafafa\"".length());
            int indexPhoneBefore = result.indexOf("<p>");
            int indexPhoneAfter = result.indexOf("</p>");
            int indexHrefBefore = result.indexOf("data-plancode=\"");
            int indexHrefAfter = result.indexOf("\" data-region");
            String item = result.substring(indexPhoneBefore, indexPhoneAfter);
            String plancode = result.substring(indexHrefBefore + "data-plancode=\"".length(), indexHrefAfter);
            item = item.trim();
            String phone = "";
            if(item != null && !"".equals(item)) {
                for (int i = 0; i < item.length(); i++) {
                    if (item.charAt(i) >= 48 && item.charAt(i) <= 57) {
                        phone += item.charAt(i);
                    }
                }
            }
            ItemRule itemRule = MobileRule.checkPhone(phone);
            String tag = "";
            String remark = "";
            if(itemRule != null){
                tag = itemRule.getTag();
                Map<String, String> map = new HashMap<>();
                map.put("tag", itemRule.getRemark());
                remark = JsonMapper.toJson(map);
            }
            UserAccountPhone userAccountPhone = new UserAccountPhone();
            userAccountPhone.setPhone(phone);
            userAccountPhone.setBusinessId(getBusinessId());
            userAccountPhone.setUrl(plancode);
            userAccountPhone.setPrice(BigDecimal.ZERO);
            userAccountPhone.setType("Mobile");
            userAccountPhone.setStatus("private");
            userAccountPhone.setCityId(getCityId());
            userAccountPhone.setTag(tag);
            userAccountPhone.setRemark(remark);
            userAccountPhone.setCreateTime(new Date());
            userAccountPhone.setUpdateTime(new Date());
            list.add(userAccountPhone);
            System.out.println(phone + "   plancode:" + plancode + "    row:"+row+"   放入list");
        }
        if(list != null && list.size() > 0){
            int num = DataMapperUtil.insertUserAccountPhoneSelectiveBatch(list);
            if(num > 1){
                setUpdateNum(getUpdateNum()+1);
                System.out.println("list:"+list.size()+"   处理成功    num:"+num);
            }else{
                setFailNum(getFailNum()+1);
                System.out.println("list:"+list.size()+"   处理失败    num:"+num);
            }

            //发送通知
            List<String> phones = new ArrayList<>();
            for(UserAccountPhone phone : list){
                phones.add(phone.getPhone());
            }
            SendAlertUtil.checkAndSendAlert(getCityId(), phones);
        }
    }

    @Override
    public void execute(Integer pageStart) throws Exception {
        getPhoneAndHref(0);
    }
}