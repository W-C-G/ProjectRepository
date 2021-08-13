package com.example.demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpConnectionUtil {
	
	public void sendWriteSignal(String targetUrl, String address, int uno) throws Exception {
		// 접속할 URL
		URL url = new URL(targetUrl+"/writeDB/?address="+address+"&uno="+Integer.toString(uno));
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		if(con.getResponseCode()!= 200) {
			throw new RuntimeException("Failed: HTTP error code: "+con.getResponseCode());
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String output;
		System.out.println("Output From Server...");
		while((output=br.readLine()) != null) {
			System.out.println(output);
		}
		con.disconnect();
	}
	
	
}
