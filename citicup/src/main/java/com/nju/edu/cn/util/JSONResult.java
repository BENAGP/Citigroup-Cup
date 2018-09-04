package com.nju.edu.cn.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by shea on 2018/9/2.
 */
public class JSONResult {
    public static String fillResultString(Integer status, String message, Object result) {
        JSONObject jsonObject = new JSONObject() {{
            put("status", status);
            put("message", message);
            put("result", result);
        }};
        return jsonObject.toString();
    }
}
