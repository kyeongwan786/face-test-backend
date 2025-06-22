package com.kwr.spring.facetestbackend2.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    private Long id;
    private Long postId;
    private String nickname;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
