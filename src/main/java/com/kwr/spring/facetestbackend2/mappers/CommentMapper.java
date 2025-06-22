package com.kwr.spring.facetestbackend2.mappers;

import com.kwr.spring.facetestbackend2.entities.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insert(CommentEntity comment);
    List<CommentEntity> selectByPostId(@Param("postId") Long postId);
    CommentEntity selectById(@Param("id") Long id);
    void update(CommentEntity comment);
    void delete(@Param("id") Long id);
}
