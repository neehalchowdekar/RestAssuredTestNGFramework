package com.spotify.oauth2.api.applicationApi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.HashMap;

import com.spotify.oauth2.pojo.Playlist;


public class RestResource {

	public static Response post(Object request, String path, String access_token) {

		return given(SpecBuilder.getRequestSpec())
				.body(request)
				.auth()
				.oauth2(access_token)
				.when()
				.post(path).then().spec(SpecBuilder.getResponseSpec()).extract()
				.response();

	}
	
	public static Response post(Object request, String path) {

		return given(SpecBuilder.getReqRes())
				.body(request)
				.when()
				.post(path)
				.then()
				.spec(SpecBuilder.getResponseSpec()).extract()
				.response();

	}
	
	
	public static Response postAccount(HashMap<String, String> formParams) {
		return given(SpecBuilder.getAccountRequestSpec()).
		formParams(formParams).
		when().post(Route.API + Route.TOKEN).
		then().spec(SpecBuilder.getResponseSpec()).
		extract().
		response();
	}
	
	public static Response get(String path, String access_token) {
		return given(SpecBuilder.getRequestSpec())
				.auth().oauth2(access_token).
		when().
			get(path).
		then().
			spec(SpecBuilder.getResponseSpec()).
			extract().
			response();
	}
	
	public static Response update(Object requestPlaylist, String path, String access_token) {
		
		return given(SpecBuilder.getRequestSpec()).
				body(requestPlaylist).
				auth().oauth2(access_token).
				when().
				put(path).
				then().
				spec(SpecBuilder.getResponseSpec()).
				extract().
				response();
		
	}

}
