package adb.ws.restassured.test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UserWebServiceEndpointTest {

	private final String CONTEXT_PATH = "/adb-ws";
	private final String EMAIL_ADDRESS = "rchaudhary@mum.edu";
	private final String JSON = "application/json";
	private static String authorizationHeader;
	private static String userId;

	@BeforeEach
	void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	/*
	 * Test User Login
	 */
	@Test
	void a() {

		Map<String, String> loginDetails = new HashMap<>();
		loginDetails.put("email", EMAIL_ADDRESS);
		loginDetails.put("password", "12345");

		Response response = given().contentType(JSON).accept(JSON).body(loginDetails).when()
				.post(CONTEXT_PATH + "/users/login").then().statusCode(200).extract().response();

		authorizationHeader = response.header("Authorization");
		userId = response.header("UserID");

		assertNotNull(authorizationHeader);
		assertNotNull(userId);
	}

	/*
	 * Test Get User Details
	 */
	@Test
	void b() {

		Response response = given().header("Authorization", authorizationHeader).accept(JSON).when()
				.get(CONTEXT_PATH + "/users/" + userId).then().statusCode(200).contentType(JSON).extract().response();
		
		String userPublicId = response.jsonPath().getString("userId");
		String userEmail = response.jsonPath().getString("email");
		
		assertNotNull(userPublicId);
		assertNotNull(userEmail);
	}
}
