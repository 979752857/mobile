package com.tendy.dao.bean;

public class SysCmsRoleMenu {
    private Integer sysRoleMenuId;

    private Integer sysRoleId;

    private Integer sysMenuId;

    private Byte isleaf;

    public Integer getSysRoleMenuId() {
        return sysRoleMenuId;
    }

    public void setSysRoleMenuId(Integer sysRoleMenuId) {
        this.sysRoleMenuId = sysRoleMenuId;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Integer getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(Integer sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    public Byte getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Byte isleaf) {
        this.isleaf = isleaf;
    }
}