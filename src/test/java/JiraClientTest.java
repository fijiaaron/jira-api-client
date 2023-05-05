import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import oneshore.JiraClient;
import oneshore.JiraServerInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JiraClientTest
{
	String JIRA_BASE_URL = "https://oneshore.atlassian.net";
	JiraClient jira;


	@BeforeEach
	public void setup()
	{
		jira = new JiraClient(JIRA_BASE_URL);
	}

	@Test
	public void testCreate()
	{
		assertThat(jira).isNotNull();
	}

	@Test
	public void testServerInfo() throws JsonProcessingException
	{
		JiraServerInfo serverInfo = jira.getServerInfo();
		System.out.println("serverInfo: " + serverInfo);
		assertThat(serverInfo).isNotNull();

		Response response = jira.lastResponse.get();
		assertThat(response.statusCode()).isEqualTo(200);

		String body = response.body().asString();
		assertThat(body).isNotEmpty();

		System.out.println(body);

		JsonPath json = response.jsonPath();
		String baseUrl = json.getString("baseUrl");
		String serverTitle = json.getString("serverTitle");
		String deploymentType = json.getString("deploymentType");

		assertThat(serverInfo.baseUrl).isEqualTo(JIRA_BASE_URL);
		assertThat(serverInfo.serverTitle).isEqualTo("Jira");
		assertThat(serverInfo.deploymentType).isEqualTo("Cloud");
		System.out.println(serverInfo);



	}
}
