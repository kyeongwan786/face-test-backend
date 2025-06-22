package com.kwr.spring.facetestbackend2.services;

import com.kwr.spring.facetestbackend2.entities.CommentEntity;
import com.kwr.spring.facetestbackend2.mappers.CommentMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentMapper commentMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<CommentEntity> getByPageType(String pageType) {
        return commentMapper.getByPageType(pageType);
    }

    public void create(String pageType, String nickname, String password, String content) {
        String hash = encoder.encode(password);
        CommentEntity comment = CommentEntity.builder()
                .pageType(pageType)
                .nickname(nickname)
                .passwordHash(hash)
                .content(content)
                .build();
        commentMapper.insert(comment);
    }

    public boolean update(Long id, String rawPassword, String newContent) {
        List<CommentEntity> all = commentMapper.getByPageType("dummy"); // 전체 조회 안 해도 되지만 간단한 방법
        for (CommentEntity c : all) {
            if (c.getId().equals(id) && encoder.matches(rawPassword, c.getPasswordHash())) {
                return commentMapper.updateContent(id, newContent, c.getPasswordHash()) > 0;
            }
        }
        return false;
    }

    public boolean delete(Long id, String rawPassword) {
        List<CommentEntity> all = commentMapper.getByPageType("dummy");
        for (CommentEntity c : all) {
            if (c.getId().equals(id) && encoder.matches(rawPassword, c.getPasswordHash())) {
                return commentMapper.softDelete(id, c.getPasswordHash()) > 0;
            }
        }
        return false;
    }
}
