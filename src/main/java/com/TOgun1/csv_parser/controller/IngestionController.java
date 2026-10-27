package com.TOgun1.csv_parser.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TOgun1.csv_parser.service.Ingestion1;

@RestController
@RequestMapping("/api/v1/upload")
public class IngestionController {

    private final Ingestion1 slowIngestionService;

    public IngestionController(Ingestion1 slowIngestionService) {
        this.slowIngestionService = slowIngestionService;
    }

    @PostMapping("/slow")
    public Map<String, Object> uploadSlow(@RequestParam String filePath) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        slowIngestionService.ingestData(filePath);

        stopWatch.stop();
        Map<String, Object> response = new HashMap<>();
        response.put("executionTimeSeconds", stopWatch.getTotalTimeSeconds());
        return response;
    }
}
