package com.nju.edu.cn.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.nju.edu.cn.constant.APIConstant;
import com.nju.edu.cn.exception.InvalidRequestException;
import com.nju.edu.cn.model.APIContext;
import com.nju.edu.cn.model.AuthorizeResponse;
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
import javax.servlet.http.HttpSession;

public class GetAccounts {

	private static Logger logger = LoggerFactory.getLogger(GetAccounts.class);

	public static String getAccounts(String realAccessToken, HttpSession session) throws IOException{
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
		AuthorizeResponse authorizeResponse = JSON.parseObject(responseBodyString,AuthorizeResponse.class);
		if(authorizeResponse.getType()==null){
			System.out.println("step4 accounts:");
			System.out.println("\t"+responseBodyString);
			return responseBodyString;
		}else {
			GetAuthorize.authorize(session,authorizeResponse);
		}
		return "";
	}

	public static String getAccountDetails(APIContext context,HttpSession session) throws IOException{
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
		AuthorizeResponse authorizeResponse = JSON.parseObject(responseBodyString,AuthorizeResponse.class);
		if(authorizeResponse.getType()==null){
			context.setAccounts(responseBodyString);
			System.out.println("step5 account details:");
			System.out.println("\t"+responseBodyString);
			return responseBodyString;
		}else {
			GetAuthorize.authorize(session,authorizeResponse);
		}
		return "";

	}

	public static String getTransaction(APIContext context,HttpSession session) throws IOException{
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
		AuthorizeResponse authorizeResponse = JSON.parseObject(responseBodyString,AuthorizeResponse.class);
		if(authorizeResponse.getType()==null){
			context.setAccounts(responseBodyString);
			System.out.println("step6 transaction details:");
			System.out.println("\t"+responseBodyString);
			return responseBodyString;
		}else {
			GetAuthorize.authorize(session,authorizeResponse);
		}
		return "";

	}


	public static String getTransferCombine(String realAccessToken,HttpSession session) throws IOException{
		String client_id = APIConstant.CLIENT_ID;
		String authorization = "Bearer " + realAccessToken;
		System.out.println("real_access_token:===="+realAccessToken);
		UUID uuid = UUID.randomUUID();
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
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
		AuthorizeResponse authorizeResponse = JSON.parseObject(responseBodyString,AuthorizeResponse.class);
		if(authorizeResponse.getType()==null){
			System.out.println("step7 transfer combine:");
			System.out.println("\t"+responseBodyString);
			return responseBodyString;
		}else {
			GetAuthorize.authorize(session,authorizeResponse);
		}
		return "";

	}

	public static String getPayeeCombine(String realAccessToken,HttpSession session) throws IOException{
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
		AuthorizeResponse authorizeResponse = JSON.parseObject(responseBodyString,AuthorizeResponse.class);
		if(authorizeResponse.getType()==null){
			System.out.println("getPayeeCombine:");
			System.out.println("\t"+responseBodyString);
			return responseBodyString;
		}else {
			GetAuthorize.authorize(session,authorizeResponse);
		}
		return "";

	}

	public static String transferPreProgress(String realAccessToken,String body,HttpSession session) throws IOException{
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
		AuthorizeResponse authorizeResponse = JSON.parseObject(responseBodyString,AuthorizeResponse.class);
		if(authorizeResponse.getType()==null){
			System.out.println("transferPreProgress:");
			System.out.println("\t"+responseBodyString);
			return responseBodyString;
		}else {
			GetAuthorize.authorize(session,authorizeResponse);
		}
		return "";
	}

	public static String transferConfirm(String realAccessToken,String body,HttpSession session) throws IOException{
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
		AuthorizeResponse authorizeResponse = JSON.parseObject(responseBodyString,AuthorizeResponse.class);
		if(authorizeResponse.getType()==null){
			System.out.println("transferConfirm:");
			System.out.println("\t"+responseBodyString);
			return responseBodyString;
		}else {
			GetAuthorize.authorize(session,authorizeResponse);
		}
		return "";

	}

	public static String getBasic(String realAccessToken,HttpSession session) throws IOException {
		String client_id = APIConstant.CLIENT_ID;
		String authorization = "Bearer " + realAccessToken;
		UUID uuid = UUID.randomUUID();
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://sandbox.apihub.citi.com/gcb/api/v1/customers/profiles/basic")
				.get()
				.addHeader("authorization", authorization)
				.addHeader("uuid", uuid.toString())
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.addHeader("client_id", client_id)
				.build();
		Response response = client.newCall(request).execute();
		logger.info("get-basic-response: {}",JSON.toJSONString(response));
		String responseBodyString = response.body().string();
		AuthorizeResponse authorizeResponse = JSON.parseObject(responseBodyString,AuthorizeResponse.class);
		logger.info("basic:{}",JSON.toJSONString(authorizeResponse));
		if(authorizeResponse.getType()==null){
			System.out.println("basic_info:");
			System.out.println("\t"+responseBodyString);
			CustomerBasic customerBasic = JSON.parseObject(responseBodyString,CustomerBasic.class);
			CustomerParticular customerParticular = customerBasic.getCustomerParticulars();
			String prefix = customerParticular.getPrefix().substring(0,1).toUpperCase()+customerParticular.getPrefix().toLowerCase().substring(1);
			String lastName = customerParticular.getNames()[0].getLastName().substring(0,1).toUpperCase()+customerParticular.getNames()[0].getLastName().substring(1).toLowerCase();
			String nickname = prefix+"."+lastName;
			return nickname;
		}else {
			GetAuthorize.authorize(session,authorizeResponse);
		}
		return "";

	}



}
