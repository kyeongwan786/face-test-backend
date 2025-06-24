package com.kwr.spring.facetestbackend2.results;

public enum CommonResult {
    SUCCESS,                // 요청 성공
    FAILURE,                // 일반 실패
    FAILURE_INVALID,        // 유효하지 않은 요청
    FAILURE_NOT_FOUND,      // 대상 없음
    FAILURE_UNAUTHORIZED,   // 인증 실패 (비밀번호 틀림 등)
    FAILURE_CONFLICT,       // 중복 등 충돌
    FAILURE_SERVER_ERROR    // 내부 서버 오류
}
