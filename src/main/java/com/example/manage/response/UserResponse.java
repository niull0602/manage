package com.example.manage.response;

import lombok.Data;

import java.time.Instant;

@Data
public class UserResponse {
    private Long id;

    private String phoneNumber;

    private String password;

    private String nickName;

    private Instant createTime;

    private Instant updateTime;

    private Short mark;

    private String roleName;


}
