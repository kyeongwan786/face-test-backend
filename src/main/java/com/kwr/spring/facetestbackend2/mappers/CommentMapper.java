package com.kwr.spring.facetestbackend2.mappers;

import com.kwr.spring.facetestbackend2.entities.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 댓글 목록
    List<CommentEntity> selectByPostId(@Param(value = "postId") Long postId,
                                       @Param(value = "limit") int limit,
                                       @Param(value = "offset") int offset);

    // 전체 개수
    int countByPostId(@Param(value = "postId") Long postId);

    // 댓글 등록
    int insert(CommentEntity comment);

    // 댓글 수정
    int updateContent(@Param(value = "id") Long id,
                      @Param(value = "password") String password,
                      @Param(value = "content") String newContent);

    // 댓글 삭제
    int softDelete(@Param(value = "id") Long id,
                   @Param(value = "password") String password);

    CommentEntity selectById(@Param("id") Long id);

}
