package com.spotify.oauth2.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spotify.oauth2.api.applicationApi.UserApi;
import com.spotify.oauth2.pojo.Users;

//import io.github.sskorol.core.DataSupplier;
//import io.github.sskorol.data.JsonReader;
//import io.github.sskorol.data.TestDataReader;
import io.restassured.response.Response;
//import one.util.streamex.StreamEx;

public class UserTest extends BaseTest {
	
	@Test(dataProvider = "test-data1", timeOut = 4000)
	public void createUsersTest(Users users) {
		Users requestUsers = Users.builder().name(users.getName()).job(users.getJob()).build();
		Response response = UserApi.post(requestUsers);
		assertThat(response.getStatusCode(), equalTo(201));
		assertUsersEquals(response.as(Users.class), requestUsers);
	}

	private void assertUsersEquals(Users responseUsers, Users requestUsers) {
		assertThat(responseUsers.getName(), equalTo(requestUsers.getName()));
		assertThat(responseUsers.getJob(), equalTo(requestUsers.getJob()));
		
	}
	
	@Test(dataProvider = "getData2")
	public void testdata(Users users) {
		System.out.println(users.getName());
		System.out.println(users.getJob());
	}
	
	@DataProvider(name = "test-data")
	public Object[][] getData(){
		return new Object[][] {
				{"ronaldo", "player"},{"admin", "admin"}
		};
	}
	
	@DataProvider(name = "test-data1")
	public Object[] getData1() throws JsonParseException, JsonMappingException, IOException {
		List<Users> users = new ObjectMapper()
				.readValue(new File(System.getProperty("user.dir")+"/src/test/resources/users.json"), new TypeReference<List<Users>>(){});
		return users.toArray();
	}
	
//	@DataSupplier
//	public StreamEx<Users> getData2(){
//		return TestDataReader.use(JsonReader.class).withTarget(Users.class).withSource("users.json").read();
//	}
//	
	

}
