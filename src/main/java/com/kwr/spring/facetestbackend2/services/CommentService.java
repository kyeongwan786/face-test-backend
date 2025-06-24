package com.kwr.spring.facetestbackend2.services;

import com.kwr.spring.facetestbackend2.entities.CommentEntity;
import com.kwr.spring.facetestbackend2.mappers.CommentMapper;
import com.kwr.spring.facetestbackend2.results.CommonResult;
import com.kwr.spring.facetestbackend2.utils.CryptoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    // ✅ 댓글 목록
    public Map<String, Object> getComments(Long postId, int page, int size) {
        if (postId == null || page <= 0 || size <= 0) {
            return error(CommonResult.FAILURE_INVALID, "유효하지 않은 요청입니다.");
        }

        int offset = (page - 1) * size;
        List<CommentEntity> comments = commentMapper.selectByPostId(postId, size, offset);
        int total = commentMapper.countByPostId(postId);

        return Map.of(
                "result", CommonResult.SUCCESS,
                "comments", comments,
                "totalCount", total,
                "currentPage", page
        );
    }

    // ✅ 댓글 등록
    public Map<String, Object> addComment(CommentEntity comment) {
        if (comment == null) return error(CommonResult.FAILURE_INVALID, "요청이 비어 있습니다.");
        if (comment.getPostId() == null) return error(CommonResult.FAILURE_INVALID, "postId는 필수입니다.");
        if (isEmpty(comment.getNickname())) return error(CommonResult.FAILURE_INVALID, "닉네임은 필수입니다.");
        if (isEmpty(comment.getPassword())) return error(CommonResult.FAILURE_INVALID, "비밀번호는 필수입니다.");
        if (isEmpty(comment.getContent())) return error(CommonResult.FAILURE_INVALID, "내용은 필수입니다.");

        comment.setPassword(CryptoUtils.hashSha512(comment.getPassword()));

        try {
            int inserted = commentMapper.insert(comment);
            if (inserted == 1) {
                return success("댓글이 등록되었습니다.");
            } else {
                return error(CommonResult.FAILURE, "댓글 등록 실패");
            }
        } catch (Exception e) {
            return error(CommonResult.FAILURE, "DB 오류: " + e.getMessage());
        }
    }

    // ✅ 댓글 수정
    public Map<String, Object> updateComment(Long id, String password, String content) {
        if (id == null) return error(CommonResult.FAILURE_INVALID, "댓글 ID는 필수입니다.");
        if (isEmpty(password)) return error(CommonResult.FAILURE_INVALID, "비밀번호는 필수입니다.");
        if (isEmpty(content)) return error(CommonResult.FAILURE_INVALID, "내용은 필수입니다.");

        CommentEntity dbComment = commentMapper.selectById(id);
        if (dbComment == null) return error(CommonResult.FAILURE_NOT_FOUND, "댓글이 존재하지 않습니다.");
        if (Boolean.TRUE.equals(dbComment.getDeleted())) return error(CommonResult.FAILURE_NOT_FOUND, "삭제된 댓글입니다.");

        String hashedInputPw = CryptoUtils.hashSha512(password);
        if (!hashedInputPw.equals(dbComment.getPassword())) {
            return error(CommonResult.FAILURE_UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        try {
            int updated = commentMapper.updateContent(id, password, content);
            if (updated == 1) {
                return success("댓글이 수정되었습니다.");
            } else {
                return error(CommonResult.FAILURE, "댓글 수정 실패");
            }
        } catch (Exception e) {
            return error(CommonResult.FAILURE, "DB 오류: " + e.getMessage());
        }
    }

    // ✅ 댓글 삭제
    public Map<String, Object> deleteComment(Long id, String password) {
        if (id == null) return error(CommonResult.FAILURE_INVALID, "댓글 ID는 필수입니다.");
        if (isEmpty(password)) return error(CommonResult.FAILURE_INVALID, "비밀번호는 필수입니다.");

        CommentEntity dbComment = commentMapper.selectById(id);
        if (dbComment == null) return error(CommonResult.FAILURE_NOT_FOUND, "댓글이 존재하지 않습니다.");
        if (Boolean.TRUE.equals(dbComment.getDeleted())) return error(CommonResult.FAILURE_NOT_FOUND, "이미 삭제된 댓글입니다.");

        String hashedInputPw = CryptoUtils.hashSha512(password);
        if (!hashedInputPw.equals(dbComment.getPassword())) {
            return error(CommonResult.FAILURE_UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        try {
            int deleted = commentMapper.softDelete(id, password);
            if (deleted == 1) {
                return success("댓글이 삭제되었습니다.");
            } else {
                return error(CommonResult.FAILURE, "댓글 삭제 실패");
            }
        } catch (Exception e) {
            return error(CommonResult.FAILURE, "DB 오류: " + e.getMessage());
        }
    }

    // ✅ 공통 유틸
    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private Map<String, Object> success(String msg) {
        return Map.of("result", CommonResult.SUCCESS, "message", msg);
    }

    private Map<String, Object> error(CommonResult code, String msg) {
        return Map.of("result", code, "message", msg);
    }
}


