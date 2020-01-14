package com.ak.restfulExample.testUtils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ak.restfulExample.Item;

public class JerseyClient {

	private static final String BASE_ADDRESS = "http://localhost:8080/restfulExample/webapi/";
	private static Client client = ClientBuilder.newClient();
	
	public static Response postClient(String param) {
	    return client
	      .target(BASE_ADDRESS+"insert/")
	      .request(MediaType.APPLICATION_JSON)
	      .post(Entity.entity(param, MediaType.APPLICATION_JSON));
	}

	public static void main(String[] args) {
		String param = "{"
				+ "\"brand\":\"Hugo Boss\","
				+ "\"category\":\"apparel\","
				+ "\"description\":\"Red hugo boss shirt\","
				+ "\"name\":\"Red Shirt\","
				+ "\"tags\":["
				+ "\"red\",\"shirt\",\"slim fit\"]"
				+ "}";
		Response r = postClient(param);
		System.out.println(r.getStatus());
		System.out.println(r.readEntity(Item.class));  // public setter avoided for Item.createdAt
	}

}
