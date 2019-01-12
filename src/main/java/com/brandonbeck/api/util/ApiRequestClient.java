package com.brandonbeck.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ApiRequestClient {

	public int responseCode = 0;
	public boolean isError = false;
	public Map<String, List<String>> responseHeaders;
	
	
	public String establishURLConnection(String url, HttpMethod requestMethod, String requestBody, KeyValuePair... headers) throws IOException {
		String response = "";
		URL urlConnection = new URL(url);
		HttpsURLConnection connection = (HttpsURLConnection)urlConnection.openConnection();
		
		connection.setRequestMethod(requestMethod.name());
		connection.setConnectTimeout(30000);
		connection.setReadTimeout(300000);
		
		if(headers != null  && headers.length > 0) {
			for(KeyValuePair header : headers) {
				connection.setRequestProperty(header.getKey(), header.getValue());
			}
		}
		
		if(requestMethod == HttpMethod.POST && requestBody != null && !requestBody.isEmpty()) {
			connection.setDoOutput(true);
			OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
			osw.write(requestBody);
			osw.flush();
		}
		
		connection.connect();
		
		responseCode = connection.getResponseCode();
		System.out.println("ResponseCode: " + responseCode);
		String responseMessage = connection.getResponseMessage();
		System.out.println("ResponseMessage: " + responseMessage);
		
		if(responseCode /100 !=2) {
			InputStream errorStream = connection.getErrorStream();
			response = ApiUtilities.convertStreamToString(errorStream);
			isError = true;
			return response;
		}
		responseHeaders = connection.getHeaderFields();
		
		response = ApiUtilities.convertStreamToString(connection.getInputStream());
		
		return response;
	}
	
	
	public enum HttpMethod{
		GET, POST, PUT, DELETE
	}
	
}
