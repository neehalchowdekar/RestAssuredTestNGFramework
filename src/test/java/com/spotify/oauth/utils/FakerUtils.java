package com.spotify.oauth.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

	
	public static String generateName() {
		Faker faker = new Faker();
		return "Playlist" + faker.regexify("[A-Za-z0-9,_-]{10}");
		
	}
	
	public static String generateDesc() {
		Faker faker = new Faker();
		return "Playlist" + faker.regexify("[A-Za-z0-9,_-@#$]{50}");
		
	}
	
	
}
