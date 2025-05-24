package com.kwr.spring.facetestbackend2.controllers;

import com.kwr.spring.facetestbackend2.services.CountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visitor")
public class CountController {

    private final CountService countService;

    public CountController(CountService countService) {
        this.countService = countService;
    }

    @PostMapping("/increase")
    public long increaseVisitorCount() {
        return countService.increaseAndGetCount();
    }

    @GetMapping("/count")
    public long getVisitorCount() {
        return countService.getCurrentCount();
    }
}
