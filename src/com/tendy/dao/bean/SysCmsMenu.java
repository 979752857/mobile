package com.tendy.dao.bean;

public class SysCmsMenu {
    private Integer sysMenuId;

    private String menuName;

    private String menuPid;

    private String menuUrl;

    private String menuIconUrl;

    private Byte isfunction;

    private Integer level;

    private Integer sequence;

    private String menuCode;

    private Byte isleaf;

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
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(String menuPid) {
        this.menuPid = menuPid == null ? null : menuPid.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuIconUrl() {
        return menuIconUrl;
    }

    public void setMenuIconUrl(String menuIconUrl) {
        this.menuIconUrl = menuIconUrl == null ? null : menuIconUrl.trim();
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

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public Byte getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Byte isleaf) {
        this.isleaf = isleaf;
    }
}