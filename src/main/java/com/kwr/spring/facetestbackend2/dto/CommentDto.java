package com.kwr.spring.facetestbackend2.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long postId;
    private String nickname;
    private String password;
    private String content;
}
