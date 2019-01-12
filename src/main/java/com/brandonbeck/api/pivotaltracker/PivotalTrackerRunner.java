package com.brandonbeck.api.pivotaltracker;

import java.util.List;

import com.brandonbeck.api.util.ApiUtilities;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class PivotalTrackerRunner {
	
	public static void main(String[] args) throws Exception {
		PivotalTrackerAuthenticator authenticator = new PivotalTrackerAuthenticator();
		String apiToken = authenticator.getApiToken();
		
		PivotalTrackerProjects project = new PivotalTrackerProjects();
		List<String> projectIds = project.getProjectIds(apiToken);
		
		
		PivotalTrackerStories stories = new PivotalTrackerStories();
		JsonParser parser = new JsonParser();
		for(String id : projectIds) {
			String response = stories.getProjectStories(id, apiToken);
			JsonElement storiesJson = parser.parse(response);
			ApiUtilities.prettyPrintJson(storiesJson);
		}
		
	}
}
