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

public class GuangDongPhone extends Phone {

    private String server = "http://wap.gd.10086.cn";
    private String url = "http://wap.gd.10086.cn/nwap/card/cardSearch/cardlist.jsps";

    public GuangDongPhone(Integer pageSize, Integer pageStart, Integer pageEnd, Integer cityId, Integer businessId) {
        super(pageSize, pageStart, pageEnd, cityId, businessId);
    }

    @Override
    public void execute(Integer pageStart) {
        getPhoneAndHref(pageStart, (pageStart-1)*getPageSize());
    }

    public void getPhoneAndHref(Integer pageNo, int row){

        Map<String, String> param = new HashMap<>();
        param.put("city", String.valueOf(getCityId()));
        param.put("pageNo", String.valueOf(pageNo));
        param.put("pageSize", String.valueOf(getPageSize()));
        param.put("offLine", "1");
        param.put("hzhbjr", "SQD021782");
        param.put("recoempltel", "S121122007");
        param.put("newflow", "1");
        Map<String, String> header = new HashMap<>();
        header.put("Referer", "http://wap.gd.10086.cn/nwap/card/offlinesimcard/index.jsps?isdecrypt=1&hzhbjr=7755357a426555636f2f34484a59684a324c314d62413d3d&recoempltel=4f72482f364f33366c4d6436396c787939314e5370673d3d&city="+ String.valueOf(getCityId())+"&substoreid=");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
        header.put("Host", "wap.gd.10086.cn");
        header.put("Origin", "http://wap.gd.10086.cn");
        header.put("Pragma", "no-cache");
        header.put("X-Requested-With", "XMLHttpRequest");
        String result = HttpConnectionUtil.requestMethod(HttpConnectionUtil.HTTP_POST, url, HttpConnectionUtil.convertStringParamter(param), header);
        result = result.trim();
        processHtmlAndHref(result, row);
    }

    public void processHtmlAndHref(String result, int row){
        int preLength = "<td>134<i></i>2841<i></i>".length();
        int poxLength = "<span class=\"text_red\">7280</span></td>".length();
        int indexHrefBeforeLength = "<td><a href=".length()+1;
        int indexHrefAfterLength = "class=\"numberpaybtn\"".length();

        List<String> phones = new ArrayList<>();
        while(true){
            int index = result.indexOf("<span class=\"text_red\">");
            int indexHrefBefore = result.indexOf("<td><a href=");
            int indexHrefAfter = result.indexOf("class=\"numberpaybtn\"");
            if(index < 0){
                break;
            }
            String item = result.substring(index-preLength, index+poxLength);
            String href = this.server + result.substring(indexHrefBefore+indexHrefBeforeLength, indexHrefAfter-2);
            item = item.trim();
            String phone = "";
            if(item != null && !"".equals(item)) {
                for (int i = 0; i < item.length(); i++) {
                    if (item.charAt(i) >= 48 && item.charAt(i) <= 57) {
                        phone += item.charAt(i);
                    }
                }
            }
            result = result.substring(indexHrefAfter+indexHrefAfterLength);
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
            userAccountPhone.setUrl(href);
            userAccountPhone.setPrice(BigDecimal.ZERO);
            userAccountPhone.setType("Mobile");
            userAccountPhone.setStatus("private");
            userAccountPhone.setCityId(getCityId());
            userAccountPhone.setTag(tag);
            userAccountPhone.setRemark(remark);
            userAccountPhone.setCreateTime(new Date());
            userAccountPhone.setUpdateTime(new Date());
            int num = DataMapperUtil.insertUserAccountPhoneSelective(userAccountPhone);
            if(num > 1){
                setUpdateNum(getUpdateNum()+1);
                System.out.println(phone + "   href:" + href.substring(22) + "    row:"+row+"   更新成功");
            }else if(num > 0){
                setSuccessNum(getSuccessNum()+1);
                System.out.println(phone + "   href:" + href.substring(22) + "    row:"+row+"   处理成功");
            }else{
                setFailNum(getFailNum()+1);
                System.out.println(phone + "   href:" + href.substring(22) + "    row:"+row+"   处理失败");
            }
            phones.add(phone);
            row++;
        }
        SendAlertUtil.checkAndSendAlert(getCityId(), phones);
    }
}