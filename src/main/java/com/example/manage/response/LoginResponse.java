package com.example.manage.response;

import lombok.Data;

import java.time.Instant;

@Data
public class LoginResponse {
    private Long id;

    private String phoneNumber;

    private String password;

    private String nickName;

    private Instant createTime;

    private String roleName;

    private String token;

    private String msg;
}
