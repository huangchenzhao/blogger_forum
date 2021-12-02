package com.ruixin.common.entity;

import java.util.List;

/**
 * Created by ruixin on 2018/4/19.
 * Description:
 */
public class Page<T> {

    private Integer page; //当前页数

    private Integer limit; //每页条数

    private long count;  //总条数

    private List<T> data;

    private int code;

    private int pageCount;  //页数

    private String message;

    public Page(){
        this.page=1;
        this.limit=10;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getPageNum(Page page){
        if(page.count<=page.limit){
            return 1;
        }
        if (page.count>page.limit){
            return page.count%page.limit==0?(int)(page.count/page.limit):(int)(page.count/page.limit)+1;
        }
        return 0;
    }
}
