package api.Test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import com.github.javafaker.Faker;

import api.EndPoints.userEndPointsusingPropertyFile;
import api.PayLoads.User;
import io.restassured.response.Response;

public class UserTestsUsingPropertyFile {
	
	Faker faker;
	User userPayLoad;
	
	public Logger logger;
	
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();
		userPayLoad = new User();
		
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5, 10));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
		
		// Logs
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void postUserTest()
	{
	    logger.info("************** Creating User **************");
	    
		Response resp = userEndPointsusingPropertyFile.createUser(userPayLoad);
		resp.then().log().all();
		
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
		
		logger.info("************** User is Created**************");
	}
	
	@Test(priority = 2)
	public void getUserByNameTest()
	{   
		logger.info("************** Reading User Info **************");
		
		Response resp = userEndPointsusingPropertyFile.readUser(this.userPayLoad.getUsername());
		resp.then().log().all();
		AssertJUnit.assertEquals(resp.statusCode(), 200);
		
		logger.info("************** User Info Is Displayed **************");
	}
	
	@Test(priority=3)
	public void updateUserByNameTest()
	{
		logger.info("************** Updating User Info **************");
		
		// Update Data Using Payload
		
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		
		Response resp = userEndPointsusingPropertyFile.updateUser(this.userPayLoad.getUsername(),userPayLoad);
		resp.then().log().body();
		AssertJUnit.assertEquals(resp.statusCode(), 200);
		
		logger.info("************** User Info Updated **************");
		
		// Checking the Data After Updation
		
		Response responseAfterUpdate = userEndPointsusingPropertyFile.readUser(this.userPayLoad.getUsername());
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	
	@Test(priority = 4)
	public void deleteuserByNameTest()
	{
		logger.info("************** Deleting User **************");
		
		Response resp = userEndPointsusingPropertyFile.deleteUser(this.userPayLoad.getUsername());
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
		
		logger.info("************** User Info Deleted **************");
	}

}
