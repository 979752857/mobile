package com.tendy.utils;

public class LuckRule {
    private String tag;
    private String message;
    private Integer index;

    public LuckRule(String tag, String message, Integer index) {
        this.tag = tag;
        this.message = message;
        this.index = index;
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
}