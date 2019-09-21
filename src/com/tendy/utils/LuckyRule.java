package com.tendy.utils;

import com.tendy.common.GeomanticType;

public class LuckyRule {
    private String tag;
    private String message;
    private Integer index;
    private Integer geomanticType;

    public LuckyRule(String tag, String message, Integer index) {
        this.tag = tag;
        this.message = message;
        this.index = index;
        this.geomanticType = GeomanticType.getBytag(tag).getCode();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getGeomanticType() {
        return geomanticType;
    }

    public LuckyRule setGeomanticType(Integer geomanticType) {
        this.geomanticType = geomanticType;
        return this;
    }
}