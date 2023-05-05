package oneshore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JiraServerInfo extends JsonEntity<JiraServerInfo>
{
	public String baseUrl;
	public String version;
	public List<Integer> versionNumbers;
	public String deploymentType;
	public int buildNumber;
	public String buildDate;
	public String serverTime;
	public String scmInfo;
	public String serverTitle;
	public DefaultLocale defaultLocale;

	public static class DefaultLocale
	{
		public String locale;
	}
}
