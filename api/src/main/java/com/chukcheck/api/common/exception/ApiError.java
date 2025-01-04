package com.chukcheck.api.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiError {

    ALREADY_MEMBER_NAME("이미 존재하는 회원 이름입니다."),
    ALREADY_MEMBER_EMAIL("이미 존재하는 회원 이메일입니다."),

    ALREADY_PLAYER("이미 존재하는 플레이어 정보입니다."),

    ALREADY_REGION("이미 존재하는 지역 정보입니다."),

    ALREADY_SNS("이미 존재하는 SNS 정보입니다."),

    ALREADY_TEAM("이미 존재하는 팀 정보입니다."),

    ;

    private final String message;

    public String message() {
        return message;
    }
}
