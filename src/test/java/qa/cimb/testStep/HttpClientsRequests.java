package qa.cimb.testStep;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.json.simple.JSONObject;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

import qa.cimb.utility.Payload;

public class HttpClientsRequests {
	
	String token ="584fa3e1e95e8d3bcdd006c8d637b794db2b5bd2";

	// Method for Get the Api call
	public CloseableHttpResponse getApiCall(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(url);
		
		httpget.addHeader("Authorization", "Bearer 584fa3e1e95e8d3bcdd006c8d637b794db2b5bd2");
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
	
		return closeableHttpResponse;

	}

	// Method for Add New data in Api
	public HttpResponse postApiCall(String url, String payload) throws ClientProtocolException, IOException {

		HttpClient httpClient = HttpClients.createDefault();

		HttpPost request = new HttpPost(url);

		StringEntity params = new StringEntity(payload);

		request.addHeader("Content-Type", "application/json");
		request.addHeader("Authorization", "Bearer 584fa3e1e95e8d3bcdd006c8d637b794db2b5bd2");
		request.setEntity(params);

		HttpResponse response = httpClient.execute(request);

		return response;
	}

}
