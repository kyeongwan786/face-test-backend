package com.kwr.spring.facetestbackend2.controllers;

import com.kwr.spring.facetestbackend2.dto.CommentDto;
import com.kwr.spring.facetestbackend2.entities.CommentEntity;
import com.kwr.spring.facetestbackend2.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "https://facealchemy.site") // React 프론트엔드 CORS 허용
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public void addComment(@RequestBody CommentDto dto) {
        commentService.addComment(dto);
    }

    @GetMapping("/{postId}")
    public List<CommentEntity> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @PutMapping("/{id}")
    public boolean updateComment(@PathVariable Long id,
                                 @RequestParam String password,
                                 @RequestParam String content) {
        return commentService.updateComment(id, password, content);
    }

    @DeleteMapping("/{id}")
    public boolean deleteComment(@PathVariable Long id,
                                 @RequestParam String password) {
        return commentService.deleteComment(id, password);
    }
}
