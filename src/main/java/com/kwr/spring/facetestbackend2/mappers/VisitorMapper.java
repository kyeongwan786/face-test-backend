package com.kwr.spring.facetestbackend2.mappers;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface VisitorMapper {
    int insertVisit();

    int countTotal();

    int countToday();

    Map<String, Object> countDaily();
}
