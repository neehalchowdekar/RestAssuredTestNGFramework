package com.spotify.oauth2.api.applicationApi;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import com.spotify.oauth.utils.ConfigLoader;
import com.spotify.oauth2.pojo.Playlist;

public class PlaylistApi {

	public static Response post(Playlist requestPlaylist) {

		return RestResource.post(requestPlaylist,
				Route.USERS + "/" + ConfigLoader.getInstance().getUserId() + Route.PLAYLISTS, TokenManager.getToken());

	}

	public static Response post(Playlist requestPlaylist, String token) {

		return RestResource.post(requestPlaylist,
				Route.USERS + "/" + ConfigLoader.getInstance().getUserId() + Route.PLAYLISTS, token);

	}

	public static Response get(String playlistId) {

		return RestResource.get(Route.PLAYLISTS + "/" + playlistId, TokenManager.getToken());
	}

	public static Response update(Playlist requestPlaylist, String playlistId) {

		return RestResource.update(requestPlaylist, Route.PLAYLISTS + "/" + playlistId, TokenManager.getToken());

	}

}
