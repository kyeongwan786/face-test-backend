package com.kwr.spring.facetestbackend2.controllers;

import com.kwr.spring.facetestbackend2.entities.CommentEntity;
import com.kwr.spring.facetestbackend2.results.CommonResult;
import com.kwr.spring.facetestbackend2.services.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // ✅ 댓글 목록 (페이징)
    @GetMapping("/{postId}")
    public Map<String, Object> list(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return commentService.getComments(postId, page, size);
    }

    // ✅ 댓글 등록
    @PostMapping
    public Map<String, Object> create(@RequestBody CommentEntity comment) {
        return commentService.addComment(comment);
    }

    // ✅ 댓글 수정
    @PutMapping("/{id}")
    public Map<String, Object> update(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        String password = body.get("password");
        String content = body.get("content");

        if (password == null || content == null) {
            return Map.of(
                    "result", CommonResult.FAILURE_INVALID,
                    "message", "비밀번호와 내용은 필수입니다."
            );
        }

        return commentService.updateComment(id, password, content);
    }

    // ✅ 댓글 삭제
    @DeleteMapping("/{id}")
    public Map<String, Object> delete(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        String password = body.get("password");

        if (password == null) {
            return Map.of(
                    "result", CommonResult.FAILURE_INVALID,
                    "message", "비밀번호는 필수입니다."
            );
        }

        return commentService.deleteComment(id, password);
    }
}
