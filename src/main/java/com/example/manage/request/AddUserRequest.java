package com.example.manage.request;

import lombok.Data;


@Data
public class AddUserRequest {
    private Long roleId;

    private String phoneNumber;

    private String password;

    private String nickName;

    private Short mark=1;

    private Short isDeleted=0;
}
