package api.Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import api.EndPoints.userEndPoints;
import api.GenericUtilities.DataProviders;
import api.PayLoads.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

public class DataDrivenTest {
	
	@Test(priority = 1, dataProvider="Data",dataProviderClass=DataProviders.class)
	public void postUserTest(String userId, String userName, String fName, String lName, String userEmail, String pwd, String phone)
	{
		User userPayLoad = new User();
		
		userPayLoad.setId(Integer.parseInt(userId));
		userPayLoad.setUsername(userName);
		userPayLoad.setFirstName(fName);
		userPayLoad.setLastName(lName);
		userPayLoad.setEmail(userEmail);
		userPayLoad.setPassword(pwd);
		userPayLoad.setPhone(phone);
		
		Response resp = userEndPoints.createUser(userPayLoad);
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
		
		
	}
	
	@Test(priority =2, dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void deleteUserByNameTest(String userName)
	{
		Response resp = userEndPoints.deleteUser(userName);
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
	}

}
