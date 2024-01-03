package api.EndPoints;

import api.PayLoads.User;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndPointsusingPropertyFile {
		
		/**
		 * @author Bittu Kumar Sharma
		 * UserEndPoints.java
		 * Created to perform CRUD requests to the USER API.
		 * 
		 */
	
	    static ResourceBundle getURL()
	    {
	    	ResourceBundle routes = ResourceBundle.getBundle("propertiesFile");
	    	return routes;
	    }

		public static Response createUser(User payload)
		{
			String post_url = getURL().getString("post_url");
			
			Response resp = given()
			   .contentType(ContentType.JSON)
			   .accept(ContentType.JSON)
			   .body(payload)
			 
			
			.when()
			   .post(post_url);
			
			return resp;
			
		}

		
		public static Response readUser(String userName)
		{
			String get_url = getURL().getString("get_url");
			
			
			Response resp = given()
			   .pathParam("username", userName) 
			
			.when()
			   .get(get_url);
			
			return resp;
			
		}
		
		public static Response updateUser(String userName, User payload)
		{
			String update_url = getURL().getString("update_url");
			
			
			Response resp = given()
			   .contentType(ContentType.JSON)
			   .accept(ContentType.JSON)
			   .pathParam("username", userName)
			   .body(payload)
			 
			
			.when()
			   .put(update_url);
			
			return resp;
			
		}
		
		public static Response deleteUser(String userName)
		{
			String delete_url = getURL().getString("delete_url");
			
			
			Response resp = given()
			   .pathParam("username", userName) 
			
			.when()
			   .delete(delete_url);
			
			return resp;
			
		}
}
