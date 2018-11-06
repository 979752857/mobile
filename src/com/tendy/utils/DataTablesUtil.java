package com.tendy.utils;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tendy
 * @Description:
 * @Date: 2018/2/8
 */
public class DataTablesUtil {

    public static Map<String, Object> getParamMap(String aoData){
        Map<String, Object> paramMap = new HashMap<>();

        List<Map> listMap = JsonMapper.json2List(aoData, Map.class);
        for(Map<String, Object> itemMap : listMap){
            paramMap.put(String.valueOf(itemMap.get("name")), itemMap.get("value"));
        }

        return paramMap;
    }

    /**
     * 可以一直查找下一页，当返回的list长度小于页面最大长度是不显示下一页
     * @param list
     * @param map
     * @return
     */
    public static String getResultString(List list, Map<String, Object> map){
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("sEcho", Integer.valueOf(map.get("sEcho").toString())+1);
        int iDisplayStart = Integer.valueOf(map.get("iDisplayStart").toString());
        int iDisplayLength = Integer.valueOf(map.get("iDisplayLength").toString());
        int total = iDisplayStart;
        if(!CollectionUtils.isEmpty(list)){
            total += list.size();
            if(list.size() == iDisplayLength){
                total += iDisplayLength;
            }
        }
        resultMap.put("iTotalRecords", total);
        resultMap.put("iTotalDisplayRecords", total);
        resultMap.put("aaData", list);
        String result = JsonMapper.toJson(resultMap);
        return result;
    }

    public static String getResultString(List list, int total, Map<String, Object> map){
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("sEcho", Integer.valueOf(map.get("sEcho").toString())+1);
        resultMap.put("iTotalRecords", total);
        resultMap.put("iTotalDisplayRecords", total);
        resultMap.put("aaData", list);
        String result = JsonMapper.toJson(resultMap);
        return result;
    }

    /**
     * otherMap 保存在查询时需要根据条件查询其他数据的map信息
     * @param list
     * @param total
     * @param map
     * @param otherMap
     * @return
     */
    public static String getResultOtherString(List list, int total, Map<String, Object> map, Map<String, Object> otherMap){
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("sEcho", Integer.valueOf(map.get("sEcho").toString())+1);
        resultMap.put("iTotalRecords", total);
        resultMap.put("iTotalDisplayRecords", total);
        resultMap.put("aaData", list);
        resultMap.put("other",otherMap);
        String result = JsonMapper.toJson(resultMap);
        return result;
    }
}
