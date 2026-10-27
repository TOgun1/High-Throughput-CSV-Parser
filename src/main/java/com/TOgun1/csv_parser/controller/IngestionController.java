package com.TOgun1.csv_parser.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TOgun1.csv_parser.service.Ingestion1;
import com.TOgun1.csv_parser.service.Ingestion2;

@RestController
@RequestMapping("/api/v1/upload")
public class IngestionController {

    private final Ingestion1 slowIngestionService;
    private final Ingestion2 fastIngestionService;

    public IngestionController(Ingestion1 slowIngestionService, Ingestion2 fastIngestionService) {
        this.slowIngestionService = slowIngestionService;
        this.fastIngestionService = fastIngestionService;

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

    @PostMapping("/fast")
    public ResponseEntity<?> uploadFast(@RequestParam String filePath) {
        try {
            long executionTimeSeconds = fastIngestionService.uploadCsvFast(filePath);
            return ResponseEntity.ok("Fast upload completed in " + executionTimeSeconds + " seconds.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
