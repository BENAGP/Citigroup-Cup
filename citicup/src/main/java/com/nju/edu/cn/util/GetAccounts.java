package com.nju.edu.cn.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.nju.edu.cn.constant.APIConstant;
import com.nju.edu.cn.model.APIContext;
import com.nju.edu.cn.model.CustomerBasic;
import com.nju.edu.cn.model.CustomerParticular;
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

	public static String getAccounts(String realAccessToken) throws IOException{
		String client_id = APIConstant.CLIENT_ID;
		String authorization = "Bearer " + realAccessToken;
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
		System.out.println("step4 accounts:");
		System.out.println("\t"+responseBodyString);
		return responseBodyString;
	}

	public static String getAccountDetails(APIContext context) throws IOException{
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
		System.out.println("step5 account details:");
		System.out.println("\t"+responseBodyString);
		return responseBodyString;
	}

	public static String getTransaction(APIContext context) throws IOException{
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
		System.out.println("step6 transaction details:");
		System.out.println("\t"+responseBodyString);
		return responseBodyString;
	}


	public static String getTransferCombine(String realAccessToken) throws IOException{
		String client_id = APIConstant.CLIENT_ID;
		String authorization = "Bearer " + realAccessToken;
		System.out.println("real_access_token:===="+realAccessToken);
		UUID uuid = UUID.randomUUID();
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
//				.url("https://sandbox.apihub.citi.com/gcb/v1/moneyMovement/internalDomesticTransfers/payees/sourceAccounts?nextStartIndex=11")
//				.url("https://sandbox.apihub.citi.com/gcb/v1/moneyMovement/billPayments/payees/sourceAccounts")
				.url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/personalDomesticTransfers/destinationAccounts/sourceAccounts")
				.get()
				.addHeader("authorization", authorization)
				.addHeader("uuid", uuid.toString())
//				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.addHeader("client_id", client_id)
				.build();
		Response response = client.newCall(request).execute();
		String responseBodyString = response.body().string();
		System.out.println("step7 transfer combine:");
		System.out.println("\t"+responseBodyString);
		return responseBodyString;
	}

	public static String getPayeeCombine(String realAccessToken) throws IOException{
		String client_id = APIConstant.CLIENT_ID;
		String authorization = "Bearer " + realAccessToken;
		System.out.println("real_access_token:===="+realAccessToken);
		UUID uuid = UUID.randomUUID();
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/payees/sourceAccounts?paymentType=INTERNAL_DOMESTIC")
				.get()
				.addHeader("authorization", authorization)
				.addHeader("uuid", uuid.toString())
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.addHeader("client_id", client_id)
				.build();
		Response response = client.newCall(request).execute();
		String responseBodyString = response.body().string();
		System.out.println("getPayeeCombine:");
		System.out.println("\t"+responseBodyString);
		return responseBodyString;
	}

	public static String transferPreProgress(String realAccessToken,String body) throws IOException{
		String client_id = APIConstant.CLIENT_ID;
		String authorization = "Bearer " + realAccessToken;
		System.out.println("real_access_token:===="+realAccessToken);
		UUID uuid = UUID.randomUUID();
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType,body);
		Request request = new Request.Builder()
				.url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/internalDomesticTransfers/preprocess")
				.post(requestBody)
				.addHeader("authorization", authorization)
				.addHeader("uuid", uuid.toString())
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.addHeader("client_id", client_id)
				.build();
		Response response = client.newCall(request).execute();
		String responseBodyString = response.body().string();
		System.out.println("step7 transfer combine:");
		System.out.println("\t"+responseBodyString);
		return responseBodyString;
	}

	public static String transferConfirm(String realAccessToken,String body) throws IOException{
		String client_id = APIConstant.CLIENT_ID;
		String authorization = "Bearer " + realAccessToken;
		System.out.println("real_access_token:===="+realAccessToken);
		UUID uuid = UUID.randomUUID();
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType, body);
		Request request = new Request.Builder()
				.url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/internalDomesticTransfers")
				.post(requestBody)
				.addHeader("authorization", authorization)
				.addHeader("uuid", uuid.toString())
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.addHeader("client_id", client_id)
				.build();

		Response response = client.newCall(request).execute();
		String responseBodyString = response.body().string();
		System.out.println("transferConfirm:");
		System.out.println("\t"+responseBodyString);
		return responseBodyString;
	}



//	public static String preTransferPro(String username,String password,String bizToken) throws IOException{
//		String client_id = APIConstant.CLIENT_ID;
//		String client_scrent = APIConstant.CLIENT_SCRENT;
//		System.err.println("bizToken: "+bizToken);
//		String encode_key = client_id + ":" + client_scrent;
//		String authorization = "Basic " + Base64.encodeBase64String(encode_key.getBytes());
//		System.out.println(password);
//		UUID uuid = UUID.randomUUID();
//		OkHttpClient client = new OkHttpClient();
//		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//		RequestBody body = RequestBody.create(mediaType, "grant_type=password&scope=/api&username="+username+"&password="+password);
//		System.out.println(username);
//		Request request = new Request.Builder()
//				.url("https://sandbox.apihub.citi.com/gcb/api/password/oauth2/token/hk/gcb")
//				.post(body)
//				.addHeader("authorization", authorization)
//				.addHeader("bizToken", bizToken)
//				.addHeader("uuid", uuid.toString())
//				.addHeader("content-type", "application/x-www-form-urlencoded")
//				.addHeader("accept", "application/json")
//				.build();
//		Response response = client.newCall(request).execute();
//		JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
//		String realAccessToken = (String) jsonObject.get("access_token");
//		System.out.println("step3 real_access_token:");
//		System.out.println("\t" + realAccessToken);
//		getBasic(realAccessToken);
//		return realAccessToken;
//	}
}
