package com.database.data;

/**
 * @Author Chenzhao Huang
 * @CreateTime 2021-07-29 10:15
 * @Version 1.0.0
 */
public class DisplayFocus {
    private String articleName;
    private String type;
    private String userName;
    private String read;

    public DisplayFocus(String articleName, String type, String userName, String read) {
        this.articleName = articleName;
        this.type = type;
        this.userName = userName;
        this.read = read;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }
}
