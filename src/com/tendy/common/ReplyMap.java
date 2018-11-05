package com.tendy.common;

import com.tendy.utils.JsonMapper;

import java.util.HashMap;
import java.util.Map;

public class ReplyMap {

    private Map<String, Object> map;

    public ReplyMap() {
        map = new HashMap<String, Object>();
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public int getCode() {
        return map.get("code") == null ? -1 : (Integer)map.get("code") ;
    }

    public void setCode(int code) {
        map.put("code", code);
    }

    public String getMessage() {
        return map.get("message") == null ? "" : (String)map.get("message");
    }

    public void setMessage(String message) {
        map.put("message", message);
    }

    public void put(String key, Object obj) {
        map.put(key, obj);
    }

    public Object get(String key) {
        return map.get(key);
    }

    /**
     * 构造成功ReplyEntity对象
     */
    public ReplyMap success() {
        map.put("code", BusinessConstants.SERVER_SUCCESS_CODE);
        map.put("message", "");
        return this;
    }

    /**
     * 构造操作失败ReplyEntity对象
     */
    public ReplyMap fail() {
        this.fail(BusinessConstants.SERVER_ERROR_CODE, BusinessConstants.SERVER_ERROR_MSG);
        return this;
    }

    /**
     * 构造操作繁忙ReplyEntity对象
     */
    public ReplyMap busy() {
        this.fail(BusinessConstants.SERVER_BUSY_CODE, BusinessConstants.SERVER_BUSY_MSG);
        return this;
    }

    /**
     * 构造操作失败ReplyEntity对象
     */
    public ReplyMap fail(int code , String message) {
        map.put("code", code);
        map.put("message", message);
        return this;
    }

    public String toJson() {
        return JsonMapper.toJson(map);
    }

    @Override
    public String toString() {
        return toJson();
    }
}
