package com.brandonbeck.api.nasa;

import java.io.IOException;
import java.io.InputStream;

import com.brandonbeck.api.util.ApiRequestClient;
import com.brandonbeck.api.util.ApiUtilities;
import com.brandonbeck.api.util.KeyValuePair;
import com.brandonbeck.api.util.UrlBuilder;
import com.brandonbeck.api.util.ApiRequestClient.HttpMethod;
import com.brandonbeck.api.util.UrlBuilder.Protocol;

public class NasaRunner {

	public static void main(String[] args) throws IOException {
	
	String date = "2018-10-13"; 
			
		
	UrlBuilder urlBuilder = new UrlBuilder();
		
		urlBuilder = urlBuilder.setProtocol(Protocol.HTTPS)
				.setDomain("api.nasa.gov/")
				.setPath("neo/rest/v1/feed")
				.addQueryParameter(new KeyValuePair("start_date", date))
				.addQueryParameter(new KeyValuePair("end_date",date))
				.addQueryParameter(new KeyValuePair("api_key","iOz5yHAoSgsEMZZrS32ZPKPSHmw0OaUNxFJJKECe"))
				;
		
		String url = urlBuilder.build();
		
		ApiRequestClient requestClient = new ApiRequestClient();
		InputStream responseStream = requestClient.establishURLConnection(url, HttpMethod.GET, null);
		String response = ApiUtilities.convertStreamToString(responseStream);
		
		NasaResponseParser responseParser = new NasaResponseParser();
		String formattedResponse = responseParser.parseResponse(response, date);
		System.out.println(formattedResponse);
		
	}
	
	
}
