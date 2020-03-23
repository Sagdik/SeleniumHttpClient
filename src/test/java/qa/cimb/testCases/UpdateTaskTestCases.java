package qa.cimb.testCases;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import org.json.JSONException;
import org.json.JSONObject;

import qa.cimb.base.cimbBase;
import qa.cimb.testStep.HttpClientsRequests;
import qa.cimb.utility.CustomeListner;

@Listeners(CustomeListner.class)
public class UpdateTaskTestCases extends cimbBase {

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

	@Test
	public void checkUpdateApiTestCase() throws ClientProtocolException, IOException, JSONException {

		httpClientRequest = new HttpClientsRequests();

		apiUrl = pro.getProperty("updateApiUrlTask");

		url = servicesUrl + apiUrl;

		// Check what is the First content in Task Before update
		CloseableHttpResponse res = httpClientRequest.getApiCall(url);

		// Change Json to string and get data
		String responseBody = EntityUtils.toString(res.getEntity(), "UTF-8");
		JSONObject jsonObjForGetRequest = new JSONObject(responseBody);

		// Store response specific data in references variable
		String getContent = jsonObjForGetRequest.getString("content");

		// Api request to update the Content
		HttpResponse postResponse = httpClientRequest.postApiCall(url,
				"{\"content\": \"Appointment with Sagar Tripath\"}");

		// Validate Post request Working fine with status code
		int statusCode = postResponse.getStatusLine().getStatusCode();
		softAssert.assertEquals(statusCode, 204);

		/**
		 * This Condition Came here after post we need to check in get our content
		 * updated or not
		 */
		CloseableHttpResponse afterUpdateGetResponse = httpClientRequest.getApiCall(url);

		// verify the get request code
		int getStatusCode = afterUpdateGetResponse.getStatusLine().getStatusCode();
		softAssert.assertEquals(getStatusCode, 200);

		// Store response specific data in references variable
		String getResponseBody = EntityUtils.toString(afterUpdateGetResponse.getEntity(), "UTF-8");
		
		// Store data in json object to validate the string
		JSONObject jsonObjForPostRequest = new JSONObject(getResponseBody);
		
		// Change json to string and get data
		String postContent = jsonObjForPostRequest.getString("content");

		//If Content Updated Assertion will pass else it fail
		softAssert.assertNotEquals(getContent, postContent,"CONTENT NEED TO BE UPDATE IN GIVEN POST API:- ");
		softAssert.assertAll();

	}

}
