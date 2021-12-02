package com.ruixin.bean;

import com.ruixin.common.entity.DataEntity;


/**
 * 首页栏目
 */
public class Type extends DataEntity<Type>{

    private String name;

    private String url;

    private String sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

}