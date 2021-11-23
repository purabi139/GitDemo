package com.api.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.Base;
import com.api.client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.util.Util;
import com.test.data.Users;

public class PostTest extends Base {

	public PostTest() throws IOException {
	
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
	
	@Test
	public void postTest() throws ClientProtocolException, IOException
	{
		restclient=new RestClient();
		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		//jackson APi to converting of java object to json  -marshalowing
		ObjectMapper omapper=new ObjectMapper();
		Users user=new Users("purabi","leader");//expected users Object
		omapper.writeValue(new File("C:\\EclipseWorkSpace\\RestAPIFramework\\src\\main\\java\\com\\test\\data\\user.json"), user);
		
	   //Json object to Strig
		String userjsonString=omapper.writeValueAsString(user);
		System.out.println(userjsonString);
		
		 closeablehttpResponse=restclient.post(url,userjsonString,headerMap);
		 //Status code
		 int statusCode=closeablehttpResponse.getStatusLine().getStatusCode();
		 Assert.assertEquals(statusCode,base.RESPONSE_STATUS_CODE_201);
		 
		 //jsonString
		 String responseString=EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
		 JSONObject responseJson=new JSONObject(responseString);
		 
		 //json to Java object- unmarshelling
		 Users userObj=omapper.readValue(responseString, Users.class);//Actual Users object
		 System.out.println(userObj);
		 
		 
		 System.out.println(user.getName().equals(userObj.getName()));
	}

}
