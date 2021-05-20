package com.spotify.oauth2.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.oauth2.pojo.Playlist;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import com.spotify.oauth.utils.DataLoader;
import com.spotify.oauth.utils.FakerUtils;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.api.applicationApi.SpecBuilder;
import com.spotify.oauth2.api.applicationApi.StatusCode;
import com.spotify.oauth2.pojo.Error;

public class PlaylistTests extends BaseTest {

	@Test
	public void shouldBeAbleToCreatePlaylist() {

		Playlist requestPlaylist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDesc(), false);

		Response response = PlaylistApi.post(requestPlaylist);
		assertThat(response.getStatusCode(), equalTo(201));
		assertPlaylistEquals(response.as(Playlist.class), requestPlaylist);

	}

	@Test(enabled = false)
	public void shouldBeAbleToGetPlaylist() {

		Playlist requestPlaylist = playlistBuilder("Updated Playlist", "Updated playlist description", false);
		Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
		assertThat(response.getStatusCode(), equalTo(200));
		assertPlaylistEquals(response.as(Playlist.class), requestPlaylist);

	}

	@Test
	public void shouldBeAbleToPutPlaylist() {
		
		Playlist requestPlaylist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDesc(), false);
		Response response = PlaylistApi.update(requestPlaylist, DataLoader.getInstance().getUpadtedPlaylistId());
		assertStatusCode(response.getStatusCode(), StatusCode.CODE_200);
	}

	@Test
	public void shouldNotBeAbleToCreatePlaylistWithoutName() {
		Playlist requestPlaylist = playlistBuilder("", FakerUtils.generateDesc(), false);

		Response response = PlaylistApi.post(requestPlaylist);
		assertStatusCode(response.getStatusCode(), StatusCode.CODE_400);
		assertError(response.as(Error.class), StatusCode.CODE_400);

	}

	@Test
	public void shouldNotBeAbleToCreatePlaylistWithExpiredToken() {
		
		Playlist requestPlaylist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDesc(), false);


		Response response = PlaylistApi.post(requestPlaylist, "12344");
		assertStatusCode(response.getStatusCode(), StatusCode.CODE_401);
		assertError(response.as(Error.class), StatusCode.CODE_401);
	}

	public Playlist playlistBuilder(String name, String desc, boolean _public) {
		return Playlist.builder().
				name(name).
				description(desc).
				_public(_public).
				build();
	}

	public void assertPlaylistEquals(Playlist responsePlaylist, Playlist requestPlaylist) {

		assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));

	}

	public void assertStatusCode(int actualStatusCode, StatusCode statusCode) {
		assertThat(actualStatusCode, equalTo(statusCode.code));
	}

	public void assertError(Error responseErr, StatusCode statusCode) {
		assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
		assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
	}

}
