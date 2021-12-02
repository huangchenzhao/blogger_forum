package com.ruixin.bean;

import com.ruixin.common.entity.DataEntity;

/**
 * 友情链接
 */
public class Link extends DataEntity<Link>{

    private String description;

    private String url;

    private String sort;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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