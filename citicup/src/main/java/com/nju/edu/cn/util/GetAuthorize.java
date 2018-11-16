package com.nju.edu.cn.util;

import com.alibaba.fastjson.JSON;
import com.nju.edu.cn.constant.APIConstant;
import com.nju.edu.cn.exception.InvalidRequestException;
import com.nju.edu.cn.model.APIContext;
import com.nju.edu.cn.model.AuthorizeResponse;
import com.nju.edu.cn.model.CustomerBasic;
import com.nju.edu.cn.model.CustomerParticular;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by shea on 2018/10/23.
 */
public class GetAuthorize {

    private static Logger logger = LoggerFactory.getLogger(GetAuthorize.class);


    public static void authorize(HttpSession session,AuthorizeResponse authorizeResponse){
        if(authorizeResponse.unAuthorized()&&session.getAttribute("retried")==null){
            session.setAttribute("retried",true);
            try {
                String accessToken = getAccessToken();
                APIContext apiContext = getBizToken(accessToken);
                String bizToken = apiContext.getBizToken();
                String username = (String)session.getAttribute("username");
                String password = (String)session.getAttribute("password");

                getRealAccessToken(username,password,bizToken,session);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            if(session.getAttribute("retried")!=null)session.removeAttribute("retried");
            throw new InvalidRequestException("请先登录");
        }


    }

    public static String getAccessToken() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String client_id = APIConstant.CLIENT_ID;
        String client_scrent = APIConstant.CLIENT_SCRENT;
        String encode_key = client_id + ":" + client_scrent;
        String authorization = "Basic " + Base64.encodeBase64String(encode_key.getBytes());
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&scope=/api");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/clientCredentials/oauth2/token/hk/gcb")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("authorization", authorization)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String accessToken = (String) jsonObject.get("access_token");
        System.out.println("step1 access_token:");
        System.out.println("\t" + accessToken);
        return accessToken;
    }


    public static APIContext getBizToken(String accessToken) throws IOException {
//		Map<String, String> map = new HashMap<String, String>();
        OkHttpClient client = new OkHttpClient();
        String client_id = APIConstant.CLIENT_ID;
        String authorization = "Bearer " + accessToken;
        UUID uuid = UUID.randomUUID();
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/security/e2eKey")
                .get()
                .addHeader("authorization", authorization)
                .addHeader("client_id", client_id)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String modulus = null;
        String exponent = null;
        String bizToken = null;
        String eventId = null;
        APIContext context = new APIContext();
        if (jsonObject != null) {
            modulus = (String) jsonObject.get("modulus");
            exponent = (String) jsonObject.get("exponent");
            Headers headers = response.headers();
            bizToken = headers.get("bizToken");
            eventId = headers.get("eventId");
            context.setEventId(eventId);
            context.setBizToken(bizToken);
            context.setExponent(exponent);
            context.setModulus(modulus);
        }
        System.out.println("step2 map:");
        return context;
    }

    public static String getRealAccessToken(String username,String password,String bizToken,HttpSession session) throws IOException{
        String client_id = APIConstant.CLIENT_ID;
        String client_scrent = APIConstant.CLIENT_SCRENT;
        System.err.println("bizToken: "+bizToken);
        String encode_key = client_id + ":" + client_scrent;
        String authorization = "Basic " + Base64.encodeBase64String(encode_key.getBytes());
        System.out.println(password);
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=password&scope=/api&username="+username+"&password="+password);
        System.out.println(username);
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/password/oauth2/token/hk/gcb")
                .post(body)
                .addHeader("authorization", authorization)
                .addHeader("bizToken", bizToken)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String realAccessToken = (String) jsonObject.get("access_token");
        System.out.println("step3 real_access_token:");
        System.out.println("\t" + realAccessToken);
        session.setAttribute("real_access_token",realAccessToken);
        session.setAttribute("username",username);
        session.setAttribute("password",password);
//        getBasic("aaaaaaaa",session);
        return realAccessToken;
    }

}
