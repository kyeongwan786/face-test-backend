package com.kwr.spring.facetestbackend2.mappers;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountMapper {
    long getCount();

    int incrementCount();
}
