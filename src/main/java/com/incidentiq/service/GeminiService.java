package com.incidentiq.service;

import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GeminiService {

    private final Client client;

    public GeminiService() {

        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "GEMINI_API_KEY is not set"
            );
        }

        client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public String analyzeIncident(String description) {

        String prompt = """
                Analyze this software incident.

                Incident:
                %s

                Return the following:
                Category
                Severity
                Possible Cause
                Troubleshooting Suggestion
                """.formatted(description);

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        prompt,
                        null
                );

        return response.text();
    }
}