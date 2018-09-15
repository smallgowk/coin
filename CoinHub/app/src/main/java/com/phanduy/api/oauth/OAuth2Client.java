package com.phanduy.api.oauth;

import com.phanduy.GlobalInfo;

public class OAuth2Client {

	private final String username;
	private final String password;
	private final String clientId;
	private final String clientSecret;
	private final String site;

	public OAuth2Client(String username, String password, String clientId, String clientSecret, String site) {
		this.username = username;
		this.password = password;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.site = site;
	}
	public OAuth2Client(String site) {
		this.username = null;
		this.password = null;
		this.clientId = GlobalInfo.CoinmarkCalendar.CLIENT_ID;
		this.clientSecret = GlobalInfo.CoinmarkCalendar.CLIENT_SECRET;
		this.site = site;
	}
	

	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public String getClientId() {
		return clientId;
	}


	public String getClientSecret() {
		return clientSecret;
	}


	public String getSite() {
		return site;
	}
	
	public Token getAccessToken() {
		OAuth2Config oauthConfig = new OAuth2Config.OAuth2ConfigBuilder(username, password, clientId, site)
			.grantType("client_credentials").build();
		return OAuthUtils.getAccessToken(oauthConfig);
	}
}
