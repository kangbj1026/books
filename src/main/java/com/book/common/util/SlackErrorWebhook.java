package com.book.common.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.book.common.dto.response.CommonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SlackErrorWebhook
{
	@PostMapping("/api/v1/error")
	public CommonResponse<?> error(@RequestBody Map<String, String> body)
	{
		try
		{
			HttpClient http = HttpClient.newHttpClient();
			ObjectMapper(body, http);
		}
		catch (IOException | InterruptedException e)
		{
			throw new RuntimeException(e);
		}

		return CommonResponse.OK();
	}

	private static void ObjectMapper(@RequestBody Map<String, String> body, HttpClient http) throws IOException, InterruptedException {
		ObjectMapper mapper = new ObjectMapper();
		String text = mapper.writeValueAsString(body);

		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("https://hooks.slack.com/services/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"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(text))
				.build();

		var response = http.send(httpRequest, HttpResponse.BodyHandlers.ofString());
	}

	public static void sendError(String message)
	{
		try
		{
			HttpClient http = HttpClient.newHttpClient();
			HashMap<String, String> map = new HashMap<>();
			map.put("text", message);
			ObjectMapper(map, http);
		}
		catch (IOException | InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}
}
