package com.brandonbeck.api.nasa;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.brandonbeck.api.util.ApiRequestClient;
import com.brandonbeck.api.util.ApiUtilities;
import com.brandonbeck.api.util.KeyValuePair;
import com.brandonbeck.api.util.UrlBuilder;
import com.brandonbeck.api.util.ApiRequestClient.HttpMethod;
import com.brandonbeck.api.util.UrlBuilder.Protocol;

public class NasaRunner {

	public static void main(String[] args) throws Exception {
	
	String date = "2018-10-13"; 
		
	UrlBuilder urlBuilder = new UrlBuilder();
		
		urlBuilder = urlBuilder.setProtocol(Protocol.HTTPS)
				.setDomain("api.nasa.gov/")
				.setPath("neo/rest/v1/feed")
				.addQueryParameter(new KeyValuePair("start_date", date))
				.addQueryParameter(new KeyValuePair("end_date",date))
				.addQueryParameter(new KeyValuePair("api_key","DEMO_KEY"))
				;
		
		String url = urlBuilder.build();
		ApiRequestClient requestClient = new ApiRequestClient();
		
		String response = "";
		while(true) {
			response = requestClient.establishURLConnection(url, HttpMethod.GET, null);
			int remaining = Integer.parseInt(requestClient.responseHeaders.get("X-RateLimit-Remaining").get(0));
			if(remaining <= 1) {
				break;
			}
			System.out.println(remaining);
		}
		
		if(!requestClient.isError) {
		NasaResponseParser responseParser = new NasaResponseParser();
		String formattedResponse = responseParser.parseResponse(response, date);
		System.out.println(formattedResponse);
		}else {
			switch(requestClient.responseCode) {
			case 403:
				throw new Exception("Error: You have not authenticated your request correctly. Please check that you are using a valid API key and try again.");
			case 404:
				throw new Exception("Error: The Page you have requested was not found");
			case 429:
				throw new Exception("Error: Api Rate Limit Exceeded. Please wait and try again later.");
			default:
				throw new Exception("Something went wrong:" + response);
			}
			
		}
		
	}
	
	
}
