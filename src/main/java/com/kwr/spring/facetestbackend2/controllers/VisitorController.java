package com.kwr.spring.facetestbackend2.controllers;

import com.kwr.spring.facetestbackend2.services.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/visitor")
@CrossOrigin(origins = "https://facealchemy.site")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;

    @PostMapping("/record")
    public Map<String, Object> record() {
        return visitorService.recordVisit();
    }

    @GetMapping("/total")
    public Map<String, Object> total() {
        return visitorService.getTotalCount();
    }

    @GetMapping("/today")
    public Map<String, Object> today() {
        return visitorService.getTodayCount();
    }
}
