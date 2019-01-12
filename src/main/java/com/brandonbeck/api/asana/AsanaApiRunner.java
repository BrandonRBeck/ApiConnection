package com.brandonbeck.api.asana;

import java.io.InputStream;

import com.brandonbeck.api.util.ApiRequestClient;
import com.brandonbeck.api.util.ApiUtilities;
import com.brandonbeck.api.util.ApiRequestClient.HttpMethod;
import com.brandonbeck.api.util.KeyValuePair;
import com.brandonbeck.api.util.UrlBuilder;
import com.brandonbeck.api.util.UrlBuilder.Protocol;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class AsanaApiRunner {

	public static void main(String[] args) throws Exception {
		AuthToken token = new AuthToken();
		if(token.getToken() == null) {
			token.getAuthTokenFromCode("0/012f6b3acf65596b28584a7000dac1ef");
		}
		
		UrlBuilder urlBuilder = new UrlBuilder();
		
		urlBuilder = urlBuilder.setProtocol(Protocol.HTTPS)
				.setDomain("app.asana.com")
				.setPath("api/1.0/projects");
		
		ApiRequestClient request = new ApiRequestClient();
		
		String response = request.establishURLConnection(urlBuilder.build(), HttpMethod.GET, null,
				new KeyValuePair("Authorization", "Bearer " +  token.getToken()));
		JsonParser parser = new JsonParser();
		JsonElement responseJson = parser.parse(response);
		ApiUtilities.prettyPrintJson(responseJson);
	}
	
}
