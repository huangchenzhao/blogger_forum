package com.database.data;

/**
 * @Author Chenzhao Huang
 * @CreateTime 2021-07-27 20:12
 * @Version 1.0.0
 */
public class Focus {
    private Integer articleId;
    private String userName;

    public Focus(String userName, Integer articleId) {
        this.userName = userName;
        this.articleId = articleId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
