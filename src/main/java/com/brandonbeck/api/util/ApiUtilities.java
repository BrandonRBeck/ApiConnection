package com.brandonbeck.api.util;

import java.io.InputStream;
import java.util.Scanner;

public class ApiUtilities {

	public static String convertStreamToString(InputStream is) {
		String responseString = "";
		Scanner scanner = new Scanner(is, "UTF-8");
		responseString = scanner.useDelimiter("\\A").next();
		scanner.close();
		return responseString;
	}
	
}
