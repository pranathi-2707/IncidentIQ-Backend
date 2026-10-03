package com.incidentiq.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incidentiq.service.GeminiService;

@RestController
@RequestMapping("/api/ai")
public class GeminiController {

    private final GeminiService service;

    public GeminiController(GeminiService service) {
        this.service = service;
    }

    @PostMapping("/analyze")
    public String analyzeIncident(@RequestBody String description) {
        return service.analyzeIncident(description);
    }
}