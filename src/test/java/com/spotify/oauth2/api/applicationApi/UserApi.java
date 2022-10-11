package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth.utils.ConfigLoader;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.pojo.Users;

import io.restassured.response.Response;

public class UserApi {
	
	public static Response post(Users requestUsers) {
		return RestResource.post(requestUsers,Route.USERS);

	}


}
