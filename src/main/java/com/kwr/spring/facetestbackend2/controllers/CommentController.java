package com.kwr.spring.facetestbackend2.controllers;

import com.kwr.spring.facetestbackend2.entities.CommentEntity;
import com.kwr.spring.facetestbackend2.results.CommonResult;
import com.kwr.spring.facetestbackend2.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "https://facealchemy.site")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // ✅ 댓글 목록 조회
    @GetMapping("/{postId}")
    public Map<String, Object> list(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return commentService.getComments(postId, page, size);
    }

    // ✅ 댓글 작성
    @PostMapping
    public Map<String, Object> create(@RequestBody CommentEntity comment) {
        return commentService.addComment(comment);
    }

    // ✅ 댓글 수정
    @PutMapping("/{id}")
    public Map<String, Object> update(
            @PathVariable Long id,
            @RequestParam String password,
            @RequestParam String content
    ) {
        return commentService.updateComment(id, password, content);
    }

    // ✅ 댓글 삭제
    @DeleteMapping("/{id}")
    public Map<String, Object> delete(
            @PathVariable Long id,
            @RequestParam String password
    ) {
        return commentService.deleteComment(id, password);
    }
}
