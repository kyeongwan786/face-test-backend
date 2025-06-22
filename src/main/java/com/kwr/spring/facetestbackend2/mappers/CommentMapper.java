package com.kwr.spring.facetestbackend2.mappers;

import com.kwr.spring.facetestbackend2.entities.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentEntity> getByPageType(String pageType);

    int insert(CommentEntity comment);

    int updateContent(@Param(value = "id") Long id,
                      @Param(value = "content") String content,
                      @Param("passwordHash") String passwordHash
    );

    int softDelete(@Param(value = "id") Long id,
                   @Param(value = "passwordHash") String passwordHash);
}
