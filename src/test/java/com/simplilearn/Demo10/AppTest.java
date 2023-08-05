package com.simplilearn.Demo10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AppTest 
{
	static String accessToken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
			+ "eyJlbWFpbCI6InByaXlhQGdtYWlsLmNvbSIsInBhc3N3b3JkIjoiMTIz"
			+ "MzQ1IiwiaWF0IjoxNjg5NTc3ODk5LCJleHAiOjE2ODk1ODE0OTl9."
			+ "nF-Gl0mAIAcKIJQ4ZzC3ImLC8uQy4W6JUD7icnipdrI";
	String url = "http://localhost:8000/";
	private static final Logger logger = LogManager.getLogger(AppTest.class);
	
	@Test
	public void GetAccessToken() {
		logger.info("Get AccessToken Information");
		Response res = RestAssured.given().auth().oauth2(accessToken).get("http://localhost:8000/products");
		res.prettyPrint();
	}
	@Test
	public void getRequest()
	{
		logger.info("Get Restaurant Data");
	 
		given().auth().oauth2(accessToken).when().get(url+"products").then().log().body();
		logger.info("data is checked and result published");
		}
	
	@Test
	public void postRequest()
	{
		logger.info("Created a restaurant Data");	
		JSONObject request = new JSONObject();
		request.put("name","Product0009");
		request.put("cost","40");
		request.put("quantity","2000");
		request.put("locationId", "7");
		request.put("familyId", "80");
		logger.info(request);
		System.out.println(request.toString());
		
		given().auth().oauth2(accessToken).header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toString()).when().post(url+"products").then().statusCode(201).log().body();
		logger.info("data saved and transaction over");
		}
	
	
	@Test
	public void putRequest()
	{
		logger.info("Update a  Information");	
		JSONObject request = new JSONObject();
		request.put("name","Product0012");
		request.put("cost","30");
		request.put("quantity","3000");
		request.put("locationId", "3");
		request.put("familyId", "5");
		logger.info(request);
		System.out.println(request.toString());
		given().auth().oauth2(accessToken).header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		
		body(request.toString()).when().put(url+"products/10").then().statusCode(200).log().body();
		logger.info("data saved and transaction over");
		}
	
	
	@Test
	public void patchRequest()
	{
		logger.info("Rechange the Data");	
		JSONObject request = new JSONObject();
		request.put("name","Product021");
		request.put("cost","30");
		request.put("familyId", "3");
		logger.info(request);
		System.out.println(request.toString());
	
		given().auth().oauth2(accessToken).header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toString()).when().put(url+"products/10").then().statusCode(200).log().body();
		logger.info("data saved and transaction over");
		}
	
	@Test
	public void deleteRequest()
	{
		logger.info("Delete a Records");
		given().auth().oauth2(accessToken).
		when().delete(url+"products/19").then().statusCode(200).log().all();
		}
}
