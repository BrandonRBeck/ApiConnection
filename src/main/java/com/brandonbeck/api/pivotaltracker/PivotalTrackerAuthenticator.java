package com.brandonbeck.api.pivotaltracker;

import java.io.InputStream;

import com.brandonbeck.api.util.ApiRequestClient;
import com.brandonbeck.api.util.ApiUtilities;
import com.brandonbeck.api.util.ApiRequestClient.HttpMethod;
import com.brandonbeck.api.util.UrlBuilder;
import com.brandonbeck.api.util.UrlBuilder.Protocol;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PivotalTrackerAuthenticator {

	public String getApiToken() throws Exception {
		
		UrlBuilder urlBuilder = new UrlBuilder();
		
		String username = "brandonbeck1";
		String password = "UdemyAPI55";
		
		
		urlBuilder = urlBuilder
				.setProtocol(Protocol.HTTPS)
				.setDomain("www.pivotaltracker.com")
				.setPath("services/v5/me");
		
		ApiRequestClient requestClient = new ApiRequestClient();
		String response = requestClient.establishURLConnection(urlBuilder.build(), HttpMethod.GET, null, ApiUtilities.getBasicAuthHeader(username, password));
		JsonParser parser = new JsonParser();
		JsonObject responseObject = parser.parse(response).getAsJsonObject();
		String apiToken = responseObject.get("api_token").getAsString();
		
		return apiToken;
	}
	
}
