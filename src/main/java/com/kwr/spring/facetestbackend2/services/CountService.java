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
        countMapper.insertTodayIfNotExists();  // 업데이트 안 됐으면 삽입
        countMapper.incrementTotal();
    }

    public long getTodayCount() {
        return countMapper.getTodayCount();
    }

    public long getTotalCount() {
        return countMapper.getTotalCount();
    }
}
