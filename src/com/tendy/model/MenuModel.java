package com.tendy.model;

import java.util.List;

/**
 * @Author: tendy
 * @Description:
 * @Date: 2018/2/1
 */
public class MenuModel {
    private Integer sysMenuId;

    private String menuName;

    private String menuPid;

    private String menuUrl;

    private String menuIconUrl;

    private Byte isfunction;

    private Integer level;

    private Integer sequence;

    private List<MenuModel> list;

    public Integer getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(Integer sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(String menuPid) {
        this.menuPid = menuPid;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuIconUrl() {
        return menuIconUrl;
    }

    public void setMenuIconUrl(String menuIconUrl) {
        this.menuIconUrl = menuIconUrl;
    }

    public Byte getIsfunction() {
        return isfunction;
    }

    public void setIsfunction(Byte isfunction) {
        this.isfunction = isfunction;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public List<MenuModel> getList() {
        return list;
    }

    public void setList(List<MenuModel> list) {
        this.list = list;
    }
}
