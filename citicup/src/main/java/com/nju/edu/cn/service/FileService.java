package com.nju.edu.cn.service;

/**
 * Created by shea on 2018/9/8.
 */
public interface FileService {
    public void initFuturesUpdating(String path);
    public void updateFuturesUpdating(String path,Long futuresId);
    public void insertFuturesUpdating(String path);
    public void initSpotUpdating(String path);
    public void initParams(String path);
    public void initTrade(String path);
    public void initContractBackTest(String path);
    public void resetYield();
}
