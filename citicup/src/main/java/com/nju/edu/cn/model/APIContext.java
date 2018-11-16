package com.nju.edu.cn.model;

public class APIContext {
	private String accessToken;
	private String eventId;
	private String bizToken;
	private String modulus;
	private String exponent;
	private String realAccessToken;
	private String accounts;
	private String username;
	private String password;
	private String accountId;
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getBizToken() {
		return bizToken;
	}

	public void setBizToken(String bizToken) {
		this.bizToken = bizToken;
	}

	public String getRealAccessToken() {
		return realAccessToken;
	}

	public void setRealAccessToken(String realAccessToken) {
		this.realAccessToken = realAccessToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getModulus() {
		return modulus;
	}

	public void setModulus(String modulus) {
		this.modulus = modulus;
	}

	public String getExponent() {
		return exponent;
	}

	public void setExponent(String exponent) {
		this.exponent = exponent;
	}
}
