package oneshore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JiraClient
{
	String JIRA_BASE_URL;
	String JIRA_API_PATH = "/rest/api/latest";
	String SERVER_INFO_ENDPOINT = "/serverInfo";

	RequestSpecification request;
	public ThreadLocal<Response> lastResponse = new ThreadLocal<>();

	public JiraClient(String JIRA_BASE_URL)
	{
		this.JIRA_BASE_URL = JIRA_BASE_URL;
		this.request = RestAssured.given()
				.baseUri(JIRA_BASE_URL)
				.basePath(JIRA_API_PATH);
	}

	public JiraServerInfo getServerInfo() throws JsonProcessingException
	{
		Response response = request.get(SERVER_INFO_ENDPOINT);

		lastResponse.set(response);

		if (response.statusCode() != 200)
		{
			throw new RuntimeException("Didn't get OK status");
		}

		String body = response.getBody().asString();
//		response.getBody().as(JiraServerInfo.class);

		ObjectMapper mapper = new ObjectMapper();
		JiraServerInfo jiraServerInfo = mapper.readValue(body, JiraServerInfo.class);

		return jiraServerInfo;
	}
}
