package com.kwr.spring.facetestbackend2.services;

import com.kwr.spring.facetestbackend2.dto.CommentDto;
import com.kwr.spring.facetestbackend2.entities.CommentEntity;
import com.kwr.spring.facetestbackend2.mappers.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void addComment(CommentDto dto) {
        CommentEntity comment = new CommentEntity();
        comment.setPostId(dto.getPostId());
        comment.setNickname(dto.getNickname());
        comment.setPassword(dto.getPassword());
        comment.setContent(dto.getContent());
        commentMapper.insert(comment);
    }

    public List<CommentEntity> getComments(Long postId) {
        return commentMapper.selectByPostId(postId);
    }

    public boolean updateComment(Long id, String password, String newContent) {
        CommentEntity comment = commentMapper.selectById(id);
        if (comment != null && comment.getPassword().equals(password)) {
            comment.setContent(newContent);
            commentMapper.update(comment);
            return true;
        }
        return false;
    }

    public boolean deleteComment(Long id, String password) {
        CommentEntity comment = commentMapper.selectById(id);
        if (comment != null && comment.getPassword().equals(password)) {
            commentMapper.delete(id);
            return true;
        }
        return false;
    }
}