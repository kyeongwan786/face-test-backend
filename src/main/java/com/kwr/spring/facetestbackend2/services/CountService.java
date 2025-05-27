package com.kwr.spring.facetestbackend2.services;

import com.kwr.spring.facetestbackend2.mappers.CountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountService {

    private final CountMapper countMapper;

    public CountService(CountMapper countMapper) {
        this.countMapper = countMapper;
    }

    @Transactional
    public void incrementToday() {
        countMapper.updateTodayIfExists();
        countMapper.insertTodayIfNotExists();  // 없으면 새로 삽입
        countMapper.incrementTotal();
    }

    public long getTodayCount() {
        Long count = countMapper.getTodayCount();
        return count != null ? count : 0;  // null 방지 처리
    }

    public long getTotalCount() {
        Long count = countMapper.getTotalCount();
        return count != null ? count : 0;  // null 방지 처리
    }
}
