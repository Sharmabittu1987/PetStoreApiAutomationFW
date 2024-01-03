package api.EndPoints;

import api.PayLoads.User;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndPoints {
		
		/**
		 * @author Bittu Kumar Sharma
		 * UserEndPoints.java
		 * Created to perform CRUD requests to the USER API.
		 * 
		 */

		public static Response createUser(User payload)
		{
			Response resp = given()
			   .contentType(ContentType.JSON)
			   .accept(ContentType.JSON)
			   .body(payload)
			 
			
			.when()
			   .post(routes.postUrl);
			
			return resp;
			
		}

		
		public static Response readUser(String userName)
		{
			Response resp = given()
			   .pathParam("username", userName) 
			
			.when()
			   .get(routes.getUrl);
			
			return resp;
			
		}
		
		public static Response updateUser(String userName, User payload)
		{
			Response resp = given()
			   .contentType(ContentType.JSON)
			   .accept(ContentType.JSON)
			   .pathParam("username", userName)
			   .body(payload)
			 
			
			.when()
			   .put(routes.updateUrl);
			
			return resp;
			
		}
		
		public static Response deleteUser(String userName)
		{
			Response resp = given()
			   .pathParam("username", userName) 
			
			.when()
			   .delete(routes.deleteUrl);
			
			return resp;
			
		}
}
