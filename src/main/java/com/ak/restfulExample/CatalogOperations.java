package com.ak.restfulExample;

import java.util.Base64;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/insert")
public class CatalogOperations {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Item insertItem(Item newItem) {
		long now = System.currentTimeMillis();
		String encodedString = Base64.getEncoder().encodeToString((newItem.getName()+now).getBytes());
		newItem.setId(encodedString);
		newItem.setCreatedAtTime(now);
		DBServices.insertNewItem(newItem);
		return newItem;
	}
}

