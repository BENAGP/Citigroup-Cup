package com.nju.edu.cn.model;

/**
 * Created by shea on 2018/10/21.
 */
public class CustomerParticular {
    private CustomerName[] names;
    private String prefix;
    private String suffix;
    private String gender;

    public CustomerName[] getNames() {
        return names;
    }

    public void setNames(CustomerName[] names) {
        this.names = names;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
