package com.kwr.spring.facetestbackend2.controllers;

import com.kwr.spring.facetestbackend2.services.CountService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/visitor")
@CrossOrigin(origins = "https://facealchemy.site") // React 프론트엔드 CORS 허용
public class CountController {

    private final CountService countService;

    public CountController(CountService countService) {
        this.countService = countService;
    }

    @PostMapping("/increase")
    public Map<String, Long> increaseVisitorCount() {
        countService.incrementToday();
        return Map.of(
                "today", countService.getTodayCount(),
                "total", countService.getTotalCount()
        );
    }

    @GetMapping("/count")
    public Map<String, Long> getVisitorCount() {
        return Map.of(
                "today", countService.getTodayCount(),
                "total", countService.getTotalCount()
        );
    }
}
