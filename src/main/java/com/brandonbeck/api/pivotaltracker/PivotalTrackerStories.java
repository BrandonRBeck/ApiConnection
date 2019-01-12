package com.brandonbeck.api.pivotaltracker;

import java.io.InputStream;

import com.brandonbeck.api.util.ApiRequestClient;
import com.brandonbeck.api.util.ApiUtilities;
import com.brandonbeck.api.util.UrlBuilder;
import com.brandonbeck.api.util.ApiRequestClient.HttpMethod;
import com.brandonbeck.api.util.KeyValuePair;
import com.brandonbeck.api.util.UrlBuilder.Protocol;

public class PivotalTrackerStories {

	public String getProjectStories(String projectId, String apiToken) throws Exception {
		
		UrlBuilder urlBuilder = new UrlBuilder();
		
		urlBuilder = urlBuilder
				.setProtocol(Protocol.HTTPS)
				.setDomain("www.pivotaltracker.com")
				.setPath(String.format("services/v5/projects/%s/stories", projectId));
		
		ApiRequestClient requestClient = new ApiRequestClient();
		
		String response = requestClient.establishURLConnection(urlBuilder.build(), HttpMethod.GET, null,
				new KeyValuePair("X-TrackerToken",apiToken));
		
		return response;
	}
	
	
}
