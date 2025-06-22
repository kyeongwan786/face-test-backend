package com.kwr.spring.facetestbackend2.controllers;

import com.kwr.spring.facetestbackend2.entities.CommentEntity;
import com.kwr.spring.facetestbackend2.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "https://facealchemy.site")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentEntity> getComments(@RequestParam String type) {
        return commentService.getByPageType(type);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, String> payload) {
        try {
            commentService.create(
                    payload.get("pageType"),
                    payload.get("nickname"),
                    payload.get("password"),
                    payload.get("content")
            );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("작성 실패");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        boolean result = commentService.update(id, payload.get("password"), payload.get("content"));
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(403).body("비밀번호 불일치");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        boolean result = commentService.delete(id, payload.get("password"));
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(403).body("비밀번호 불일치");
    }
}
