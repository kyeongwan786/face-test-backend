package com.kwr.spring.facetestbackend2.controllers;

import com.kwr.spring.facetestbackend2.services.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class VisitorController {
    @RestController
    @RequestMapping("/api/visitor")
    @RequiredArgsConstructor
    public class visitorController {

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
}
