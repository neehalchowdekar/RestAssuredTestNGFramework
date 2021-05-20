package com.spotify.oauth2.api.applicationApi;

import java.time.Instant;
import java.util.HashMap;

import com.spotify.oauth.utils.ConfigLoader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiry_time;
	
	public synchronized static String getToken() {
		
		
		try {
			
			if(access_token == null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing expiry_token...");
				Response response = renewToken();
				access_token = response.path("access_token");
				int expireDurationInSeconds =  response.path("expires_in");
				expiry_time = Instant.now().plusSeconds(expireDurationInSeconds-300);
			}else {
				System.out.println("Token is good to use");
			}
			
		} catch (Exception e) {
			throw new RuntimeException("ABORT!! failed to renew token");
		}
		return access_token;
		
		
		
		
	}
	
	private static Response renewToken() {
		HashMap<String, String> formParams = new HashMap<String, String>();
		formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
		formParams.put("refresh_token", ConfigLoader.getInstance().getRefrestToken());
		formParams.put("client_id", ConfigLoader.getInstance().getClientId());
		formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		
		Response response = RestResource.postAccount(formParams);
		
		
		if (response.getStatusCode() != 200) {
			
		throw new RuntimeException("Unable to create access token");
		}
		return response;
		
		
		
		
	}

}
