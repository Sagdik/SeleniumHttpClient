package qa.cimb.testCases;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

import qa.cimb.base.cimbBase;
import qa.cimb.testStep.HttpClientsRequests;
import qa.cimb.utility.CustomeListner;

@Listeners(CustomeListner.class)
public class AddProjectTestCase extends cimbBase {

	
	String url;
	
	String apiUrl;

	String servicesUrl;
	
	cimbBase baseapi;
	
	SoftAssert softAssert;
	
	HttpClientsRequests httpClientRequest;
	
	/**
	 * Intialization of Property file and define base class variable
	 * @throws IOException
	 */

	@BeforeMethod
	public void intializationProperty() throws IOException {
		
		baseapi = new cimbBase();

		servicesUrl = pro.getProperty("url");
		
		softAssert = new SoftAssert();

	}
	
	/**
	 * Testing of Create New project through api
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	
	@Test
	public void createNewProject() throws ClientProtocolException, IOException, JSONException {
		
		httpClientRequest = new HttpClientsRequests();
		
		apiUrl = pro.getProperty("postApiUrlProject");
		
		url = servicesUrl+apiUrl;
		
		HttpResponse response = httpClientRequest.postApiCall(url,"{\"name\":\"movies Project\"}");
	
		//Check the status of the api
		int statusCode = response.getStatusLine().getStatusCode();
		softAssert.assertEquals(statusCode, 200);
	
		//Put response in response Body reference which used to validate data
		String responseBody =EntityUtils.toString(response.getEntity());
		System.out.println(responseBody);
	
		//Json Object store in reference object to validate the response
		JSONObject jsonObj = new JSONObject(responseBody);
        String projectName = jsonObj.getString("name");
        softAssert.assertEquals(projectName, "movies Project");
		
		softAssert.assertAll();
	}
	
	}	

