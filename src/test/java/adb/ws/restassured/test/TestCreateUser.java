package adb.ws.restassured.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

class TestCreateUser {

	private final String CONTEXT_PATH="/adb-ws";
	
	@BeforeEach
	void setUp() throws Exception {
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	void testCreateUser() {

		
	}

}
