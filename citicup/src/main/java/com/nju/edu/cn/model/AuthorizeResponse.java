package com.nju.edu.cn.model;

/**
 * Created by shea on 2018/10/28.
 */
public class AuthorizeResponse {
    private String type;
    private String code;
    private String details;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean unAuthorized(){
        if(this.type!=null) return this.type.equals("unAuthorized");
        return true;
    }
}
