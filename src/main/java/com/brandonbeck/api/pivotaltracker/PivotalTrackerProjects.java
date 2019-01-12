package com.brandonbeck.api.pivotaltracker;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.brandonbeck.api.util.ApiRequestClient;
import com.brandonbeck.api.util.ApiRequestClient.HttpMethod;
import com.brandonbeck.api.util.ApiUtilities;
import com.brandonbeck.api.util.KeyValuePair;
import com.brandonbeck.api.util.UrlBuilder;
import com.brandonbeck.api.util.UrlBuilder.Protocol;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PivotalTrackerProjects {

	public List<String> getProjectIds(String apiToken) throws Exception{

		List<String> projectIds = new ArrayList<>();
		
		UrlBuilder urlBuilder = new UrlBuilder();
		
		urlBuilder = urlBuilder
				.setProtocol(Protocol.HTTPS)
				.setDomain("www.pivotaltracker.com")
				.setPath("services/v5/projects");
		
		ApiRequestClient requestClient = new ApiRequestClient();
		String response = requestClient.establishURLConnection(urlBuilder.build(), HttpMethod.GET, null,
				new KeyValuePair("X-TrackerToken", apiToken));
		
		JsonParser parser = new JsonParser();
		JsonArray responseArray = parser.parse(response).getAsJsonArray();
		for(JsonElement project : responseArray) {
			JsonObject projectObj = project.getAsJsonObject();
			projectIds.add(projectObj.get("id").getAsString());
		}
		
		return projectIds;
	}
	
}
