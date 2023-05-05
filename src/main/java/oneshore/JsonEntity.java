package oneshore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonEntity<T extends JsonEntity>
{
	static ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

	public T fromJson(String json) throws JsonProcessingException
	{
		return (T) mapper.readValue(json, this.getClass());
	}

	public String toJson(boolean formatted) throws JsonProcessingException
	{
		if (formatted)
		{
			return toJsonFormatted();
		}

		return toJson();
	}

	public String toJson() throws JsonProcessingException
	{
		return mapper.writeValueAsString(this);
	}

	public String toJsonFormatted() throws JsonProcessingException
	{
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
	}


	@Override
	public String toString()
	{
		try
		{
			return toJson();
		}
		catch (JsonProcessingException e)
		{
			return super.toString();
		}
	}
}
