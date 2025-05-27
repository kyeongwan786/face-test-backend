package com.kwr.spring.facetestbackend2.mappers;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountMapper {
    void updateTodayIfExists();
    void insertTodayIfNotExists();
    void incrementTotal();
    long getTodayCount();
    long getTotalCount();
}
