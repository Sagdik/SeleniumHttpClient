package qa.cimb.testCases;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import qa.cimb.base.cimbBase;
import qa.cimb.testStep.HttpClientsRequests;
import qa.cimb.utility.CustomeListner;

@Listeners(CustomeListner.class)
public class AddTaskTestCase extends cimbBase {

	String url;

	String apiUrl;

	String servicesUrl;

	cimbBase baseapi;

	SoftAssert softAssert;

	HttpClientsRequests httpClientRequest;

	/**
	 * Intialization of Property file and define base class variable
	 * 
	 * @throws IOException
	 */

	@BeforeMethod
	public void intializationProperty() throws IOException {

		baseapi = new cimbBase();

		servicesUrl = pro.getProperty("url");

		softAssert = new SoftAssert();

	}

	/**
	 * Test cases to add new Task through api
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test
	public void addTaskApiTest() throws ClientProtocolException, IOException, JSONException {

		httpClientRequest = new HttpClientsRequests();

		apiUrl = pro.getProperty("postApiUrlTask");

		//Connect the api url and end url of api
		url = servicesUrl + apiUrl;

		//Create Post api and Store in response 
		HttpResponse response = httpClientRequest.postApiCall(url,
				"{\"content\": \"Appointment with Sagar Tripathi\"}");

		int statusCode = response.getStatusLine().getStatusCode();
		softAssert.assertEquals(statusCode, 200);

		// Store the body response in string json object
		String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONObject jsonObj = new JSONObject(responseBody);

		// Store response specific data in references variable
		String content = jsonObj.getString("content");

		// Validate you content by checking the content you post on task
		softAssert.assertEquals(content, "Appointment with Sagar Tripathi");

		softAssert.assertAll();

	}

}
