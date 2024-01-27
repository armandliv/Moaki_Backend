package com.example.demo.GPT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GPTService {

    private String apiKey = "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";

    private final String openaiApiUrl = "https://api.openai.com/v1/chat/completions";

    private final ObjectMapper objectMapper;

    public GPTService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getChatCompletion(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        try {
            // Create a Map representing the JSON payload
            Map<String, Object> requestBodyMap = new HashMap<>();
            requestBodyMap.put("model", "gpt-3.5-turbo");

            List<Map<String, String>> messages = new ArrayList<>();
            messages.add(Map.of("role", "system", "content", "You are a helpful assistant."));
            messages.add(Map.of("role", "user", "content", prompt));

            requestBodyMap.put("messages", messages);

            // Convert the Map to a JSON string
            String requestBody = objectMapper.writeValueAsString(requestBodyMap);

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(openaiApiUrl, requestEntity, String.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                // Handle error cases
                return "Error in API request";
            }
        } catch (HttpClientErrorException e) {
            // Handle client errors
            return "Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
        } catch (Exception e) {
            // Handle other exceptions
            return "Error in API request: " + e.getMessage();
        }
    }
}

