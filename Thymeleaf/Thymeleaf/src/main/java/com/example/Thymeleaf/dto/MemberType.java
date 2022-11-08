package com.example.Thymeleaf.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public enum MemberType {

    ADMIN("관리자", "admin"),
    USER("사용자", "user");

    private final String name;
    private final String value;

    MemberType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
