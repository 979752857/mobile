package com.tendy.dao.bean;

import java.util.Date;

public class BaseCity {
    private Integer id;

    private String cityName;

    private String cityEname;

    private String cityCharacter;

    private String province;

    private String provinceEname;

    private Integer provinceId;

    private String remark;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCityEname() {
        return cityEname;
    }

    public void setCityEname(String cityEname) {
        this.cityEname = cityEname == null ? null : cityEname.trim();
    }

    public String getCityCharacter() {
        return cityCharacter;
    }

    public void setCityCharacter(String cityCharacter) {
        this.cityCharacter = cityCharacter == null ? null : cityCharacter.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getProvinceEname() {
        return provinceEname;
    }

    public void setProvinceEname(String provinceEname) {
        this.provinceEname = provinceEname == null ? null : provinceEname.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}