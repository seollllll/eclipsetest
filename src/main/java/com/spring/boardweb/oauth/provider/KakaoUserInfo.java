package com.spring.boardweb.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements Oauth2UserInfo {
	Map<String, Object> attributes;
	Map<String, Object> properties;
	
	public KakaoUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
		this.properties = (Map<String, Object>)attributes.get("kakao_account");
	}
	
	@Override
	public String getProviderId() {
		return attributes.get("id") + "";  //"" -> String으로 형변환하기 위해
	}
	
	@Override
	public String getProvider() {
		return "kakao";
	}
	
	@Override
	public String getEmail() {
		return (String)properties.get("email"); //String으로 강제 형변환
	}
	
	@Override
	public String getName() {
		Map profile = (Map)properties.get("profile"); //Map으로 강제 형변환
		return (String)profile.get("nickname");
	}
}
