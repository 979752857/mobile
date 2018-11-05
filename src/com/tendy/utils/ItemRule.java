package com.tendy.utils;

public class ItemRule {
    private String pattern;
    private String remark;
    private String tag;

    public ItemRule(String pattern, String remark, String tag){
        this.pattern = pattern;
        this.remark = remark;
        this.tag = tag;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}