package com.simplilearn.CRUDoperation;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class AppTest 
{
	private static final Logger logger = LogManager.getLogger(AppTest.class);	
	
	@Test
	public void getData() {
		
		logger.info("Get Employees Data");
		
		baseURI = "https://reqres.in/";
		String s=given().get("api/users?page=2").asString();
		logger.info(s);
		given().get("api/users?page=2").then().statusCode(200)
		.body("data[5].id",equalTo(12))
		.body("data.email",hasItems("rachel.howell@reqres.in","michael.lawson@reqres.in","byron.fields@reqres.in"))
		.log().all();
		logger.info("data is checked and result published");	
	}
	
	@Test
	public void createData() {
		logger.info("Created a Employee Data");		
		JSONObject req = new JSONObject();
		req.put("name", "Pooja");
		req.put("job", "Developer");
		logger.info(req);
		baseURI = "https://reqres.in/";
		given().
		header("Content-Type","application/json")
		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(req.toString()).
		when().post("api/users").then().statusCode(201).log().body();	
		logger.info("data saved and transaction over");
	}
}