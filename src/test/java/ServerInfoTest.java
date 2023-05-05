import com.fasterxml.jackson.core.JsonProcessingException;
import oneshore.JiraServerInfo;
import oneshore.JsonEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ServerInfoTest
{


	@Test
	public void createFromJson() throws JsonProcessingException
	{
		String json = """
		{
			"baseUrl":"https://oneshore.atlassian.net",
			"version":"1001.0.0-SNAPSHOT",
			"versionNumbers":[1001,0,0],
			"deploymentType":"Cloud",
			"buildNumber":100225,
			"buildDate":"2023-05-04T03:13:36.000-0600",
			"serverTime":null,
			"scmInfo":"7f918b693b825208a7cd567c3a1bf9c31a2781e9",
			"serverTitle":"Jira",
			"defaultLocale":{"locale":"en_US"}
		}
		""";

		JiraServerInfo serverInfo = new JiraServerInfo().fromJson(json);
		System.out.println(serverInfo);

		assertThat(serverInfo).isNotNull();
		assertThat(serverInfo.baseUrl).isEqualTo("https://oneshore.atlassian.net");
		assertThat(serverInfo.toJson()).isNotEmpty();

		assertThat(serverInfo.defaultLocale.locale).isEqualTo("en_US");
	}

	@Test
	public void convertToJson() throws JsonProcessingException
	{
		JiraServerInfo serverInfo = new JiraServerInfo();
		serverInfo.baseUrl = "https://oneshore.atlassian.net";
		serverInfo.version = "1001.0.0-SNAPSHOT";
		serverInfo.deploymentType= "Cloud";
		serverInfo.serverTitle = "Jira";
		serverInfo.defaultLocale = new JiraServerInfo.DefaultLocale(){{ locale = "en_US"; }};

		String json = serverInfo.toJson();
		System.out.println(json);
		assertThat(json).isNotEmpty();

		String jsonFormatted = serverInfo.toJsonFormatted();
		System.out.println(jsonFormatted);
		assertThat(jsonFormatted).isNotEmpty();
		assertThat(jsonFormatted.lines().count()).isGreaterThanOrEqualTo(10);
	}
}
