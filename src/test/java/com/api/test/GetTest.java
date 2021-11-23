package com.api.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import com.api.base.Base;
import com.api.client.RestClient;
import com.qa.util.Util;


public class GetTest extends Base {
	
	


	public GetTest() throws IOException {
		
		// TODO Auto-generated constructor stub
	}

	Base base;
	String apiUrl;
	String serviceUrl;
	String url;
	RestClient restclient;
	CloseableHttpResponse closeablehttpResponse;
	
	
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		base=new Base();
		String serviceUrl=prop.getProperty("URL");
		String apiUrl=prop.getProperty("serviceURL");
		String url=serviceUrl+apiUrl;
	}
	
	public void getTest() throws ClientProtocolException, IOException
	{
		restclient=new RestClient();
		closeablehttpResponse=restclient.get(url);
		
		int statusCode=closeablehttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
		String reponseString=EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
		JSONObject responseobj=new JSONObject(reponseString);
		System.out.println(responseobj);
		
		
		//single value assertion:
		//per_page:
		String perPagevalue=Util.getvalueByJpath(responseobj, "/per_page");
		System.out.println("value of per page is-->"+ perPagevalue);
		Assert.assertEquals(Integer.parseInt(perPagevalue), 3);
		
		//
		String totalValue = Util.getvalueByJpath(responseobj, "/total");
		System.out.println("value of total is-->"+ totalValue);		
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
	
	}

	}
