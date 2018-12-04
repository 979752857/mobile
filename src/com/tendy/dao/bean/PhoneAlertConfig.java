package com.tendy.dao.bean;

import java.util.Date;

public class PhoneAlertConfig {
    private Integer id;

    private Integer cityId;

    private String tag;

    private String containsKey;

    private String sendAlert;

    private String content;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getContainsKey() {
        return containsKey;
    }

    public void setContainsKey(String containsKey) {
        this.containsKey = containsKey == null ? null : containsKey.trim();
    }

    public String getSendAlert() {
        return sendAlert;
    }

    public void setSendAlert(String sendAlert) {
        this.sendAlert = sendAlert == null ? null : sendAlert.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}