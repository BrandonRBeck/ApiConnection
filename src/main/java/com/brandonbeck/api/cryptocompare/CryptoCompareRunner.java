package com.brandonbeck.api.cryptocompare;

import java.io.IOException;
import java.io.InputStream;

import com.brandonbeck.api.util.ApiRequestClient;
import com.brandonbeck.api.util.ApiUtilities;
import com.brandonbeck.api.util.KeyValuePair;
import com.brandonbeck.api.util.UrlBuilder;
import com.brandonbeck.api.util.ApiRequestClient.HttpMethod;
import com.brandonbeck.api.util.UrlBuilder.Protocol;

public class CryptoCompareRunner {

	public static void main(String[] args) throws IOException {
		
		UrlBuilder urlBuilder = new UrlBuilder();
		
		urlBuilder = urlBuilder.setProtocol(Protocol.HTTPS)
				.setDomain("min-api.cryptocompare.com")
				.setPath("data/price")
				.addQueryParameter(new KeyValuePair("fsym", "ETH"))
				.addQueryParameter(new KeyValuePair("tsyms","USD"));
		
		String url = urlBuilder.build();
		
		ApiRequestClient requestClient = new ApiRequestClient();
		InputStream responseStream = requestClient.establishURLConnection(url, HttpMethod.GET, null);
		String response = ApiUtilities.convertStreamToString(responseStream);
		
		System.out.println("API Response: " + response);
		

	}

}
