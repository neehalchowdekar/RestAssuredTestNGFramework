package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter @Setter
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class Users {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("job")
	private String job;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("createdAt")
	private String createdAt;
	

}
