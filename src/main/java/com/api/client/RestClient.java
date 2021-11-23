package com.api.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import sun.net.www.http.HttpClient;

public class RestClient {
	
	//1. GET Method without Headers:
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient=HttpClients.createDefault();//Creates instance with default configuration
		HttpGet httpget=new HttpGet(url);//http get request
		CloseableHttpResponse closeablehttpResponse= httpclient.execute(httpget);//hit the GET Url.execute it and resturn the response
		
		return closeablehttpResponse;
			
		}
		
		
	
	//1. GET Method with Headers:
		public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpclient=HttpClients.createDefault();//Creates instance with default configuration
			HttpGet httpget=new HttpGet(url);//http get request
			
			for(Map.Entry<String, String> entry:headerMap.entrySet())
			{
				headerMap.put(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse closeablehttpResponse= httpclient.execute(httpget);//hit the GET Url.execute it and resturn the response
			
			return closeablehttpResponse;
				
			}
			
		
		//POST call with headers
		public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headerMap) throws ClientProtocolException, IOException,UnsupportedEncodingException
		{
			CloseableHttpClient httpClient=HttpClients.createDefault();
			//Creates a POST request
			HttpPost httppost=new HttpPost(url);
			//To send the payload
			httppost.setEntity(new StringEntity(entityString));
			for (Map.Entry<String,String> entry:headerMap.entrySet())
			{
				headerMap.put(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse closeablehttpResponse=httpClient.execute(httppost);
			return closeablehttpResponse;
			 
		}
	}
	
	


