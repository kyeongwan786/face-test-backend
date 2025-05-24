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
    public long increaseAndGetCount() {
        countMapper.incrementCount();
        return countMapper.getCount();
    }

    public long getCurrentCount() {
        return countMapper.getCount();
    }
}
