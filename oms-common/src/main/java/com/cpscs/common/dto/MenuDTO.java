package com.cpscs.common.dto;

import com.cpscs.common.dto.do2dto.Do2DtoUtil;

import java.io.Serializable;

public class MenuDTO implements Serializable{
    private static final long SerializableId = 0l;
    private Long menuId;
    // 父菜单ID，一级菜单为0
    private Long parentId;
    // 菜单名称
    private String name;
    // 菜单URL
    private String url;
    // 请求方式
    private String method;
    // 授权(多个用逗号分隔，如：user:list,user:create)
    private String perms;
    // 类型 0：目录 1：菜单 2：按钮
    private Integer type;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public MenuDTO buildFromDo(Object doObject){
        Do2DtoUtil<MenuDTO> util=new Do2DtoUtil<>();
        return util.doConvert(MenuDTO.class,this,doObject.getClass(),doObject);
    }


    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuId=" + menuId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", perms='" + perms + '\'' +
                ", type=" + type +
                '}'+"\n";
    }
}
