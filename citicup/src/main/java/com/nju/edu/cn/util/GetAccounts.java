package com.nju.edu.cn.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.nju.edu.cn.constant.APIConstant;
import com.nju.edu.cn.model.APIContext;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;

public class GetAccounts {

	private static Logger logger = LoggerFactory.getLogger(GetAccounts.class);
	
	public static void getBizToken(ServletContext context) throws IOException{
		step1GetAccessToken(context);
		if(context.getAttribute("access_token")!=null){
			step2GetBizToken(context);
		}
	}

	public static String getAccounts(String username, String password, APIContext context) throws IOException {
		context.setUsername(username);
		context.setPassword(password);
		step3GetRealAccessToken(context);
		if(context.getRealAccessToken()==null){
			return null;
		}
		String accounts = step4GetAccounts(context);
		if(context.getAccounts()==null){
			return null;
		}
		return accounts;
	}
	
	public static String getAccountDetail(String accountId, APIContext context) throws IOException {
		context.setAccountId(accountId);
		String accountDetail = step5GetAccountDetails(context);
		if(accountDetail == null) {
			return null;
		}
		return accountDetail;
	}
	
	public static String getTransactions(String accountId, APIContext context) throws IOException {
		context.setAccountId(accountId);
		String transaction = step6GetTransaction(context);
		if(transaction == null) {
			return null;
		}
		return transaction;
	}
	
	public static void step1GetAccessToken(ServletContext context) throws IOException {
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
		context.setAttribute("access_token",accessToken);
		logger.info("step1 access_token:{}",accessToken);
	}

	public static void step2GetBizToken(ServletContext context) throws IOException {
		OkHttpClient client = new OkHttpClient();
		String client_id = APIConstant.CLIENT_ID;
		String accessToken = context.getAttribute("access_token").toString();
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
		if (jsonObject != null) {
			modulus = (String) jsonObject.get("modulus");
			exponent = (String) jsonObject.get("exponent");
			Headers headers = response.headers();
			bizToken = headers.get("bizToken");
			eventId = headers.get("eventId");
			context.setAttribute("event_id",eventId);
			context.setAttribute("biz_token",bizToken);
			context.setAttribute("modulus",modulus);
			context.setAttribute("exponent",exponent);

		}
		logger.info("step2 modulus:{}, exponent:{}, bizToken:{}, eventId:{}",modulus,exponent,bizToken,eventId);
	}
	
	public static String step3GetRealAccessToken(APIContext context) throws IOException{
		String client_id = APIConstant.CLIENT_ID;
		String client_scrent = APIConstant.CLIENT_SCRENT;
		String bizToken = context.getBizToken();
		System.err.println("bizToken: "+bizToken);
		String encode_key = client_id + ":" + client_scrent;
		String authorization = "Basic " + Base64.encodeBase64String(encode_key.getBytes());
		String username = context.getUsername();
		String password = context.getPassword();
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
		context.setRealAccessToken(realAccessToken);
		logger.info("step3 real_access_token:{}",realAccessToken);
		return realAccessToken;
	}
	
	public static String step4GetAccounts(APIContext context) throws IOException{
		String client_id = APIConstant.CLIENT_ID;
		String authorization = "Bearer " + context.getRealAccessToken();
		UUID uuid = UUID.randomUUID();
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://sandbox.apihub.citi.com/gcb/api/v1/accounts")
				.get()
				.addHeader("authorization", authorization)
				.addHeader("uuid", uuid.toString())
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.addHeader("client_id", client_id)
				.build();
		Response response = client.newCall(request).execute();
		String responseBodyString = response.body().string();
		context.setAccounts(responseBodyString);
		logger.info("step4 accounts:{}",responseBodyString);
		return responseBodyString;
	}
	
	public static String step5GetAccountDetails(APIContext context) throws IOException{
		String client_id = APIConstant.CLIENT_ID;
		String authorization = "Bearer " + context.getRealAccessToken();
		UUID uuid = UUID.randomUUID();
		String accountId = context.getAccountId();
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://sandbox.apihub.citi.com/gcb/api/v1/accounts/"+accountId)
				.get()
				.addHeader("authorization", authorization)
				.addHeader("uuid", uuid.toString())
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.addHeader("client_id", client_id)
				.build();
		Response response = client.newCall(request).execute();
		String responseBodyString = response.body().string();
		context.setAccounts(responseBodyString);
		logger.info("step5 account details:{}",responseBodyString);
		return responseBodyString;
	}
	
	public static String step6GetTransaction(APIContext context) throws IOException{
		String client_id = APIConstant.CLIENT_ID;
		String authorization = "Bearer " + context.getRealAccessToken();
		UUID uuid = UUID.randomUUID();
		String accountId = context.getAccountId();
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://sandbox.apihub.citi.com/gcb/api/v1/accounts/"+accountId+"/transactions")
				.get()
				.addHeader("authorization", authorization)
				.addHeader("uuid", uuid.toString())
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.addHeader("client_id", client_id)
				.build();
		Response response = client.newCall(request).execute();
		String responseBodyString = response.body().string();
		context.setAccounts(responseBodyString);
		logger.info("step6 transaction details:{}",responseBodyString);
		return responseBodyString;
	}
}
