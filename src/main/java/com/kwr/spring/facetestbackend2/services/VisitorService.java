package com.kwr.spring.facetestbackend2.services;

import com.kwr.spring.facetestbackend2.mappers.VisitorMapper;
import com.kwr.spring.facetestbackend2.results.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VisitorService {
    private final VisitorMapper visitorMapper;

    @Autowired
    public VisitorService(VisitorMapper visitorMapper) {
        this.visitorMapper = visitorMapper;
    }

    // 방문 기록
    public Map<String, Object> recordVisit() {
        try {
            int inserted = visitorMapper.insertVisit();
            return Map.of(
                    "result", inserted == 1 ? CommonResult.SUCCESS : CommonResult.FAILURE,
                    "message", inserted == 1 ? "방문이 기록되었습니다." : "방문 기록 실패"
            );
        } catch (Exception e) {
            return Map.of(
                    "result", CommonResult.FAILURE,
                    "message", "DB 오류: " + e.getMessage()
            );
        }
    }

    // 전체 방문 수
    public Map<String, Object> getTotalCount() {
        try {
            int count = visitorMapper.countTotal();
            return Map.of("result", CommonResult.SUCCESS, "count", count);
        } catch (Exception e) {
            return Map.of("result", CommonResult.FAILURE, "message", "DB 오류: " + e.getMessage());
        }
    }

    // 오늘 방문 수
    public Map<String, Object> getTodayCount() {
        try {
            int count = visitorMapper.countToday();
            return Map.of("result", CommonResult.SUCCESS, "count", count);
        } catch (Exception e) {
            return Map.of("result", CommonResult.FAILURE, "message", "DB 오류: " + e.getMessage());
        }
    }
}
