package com.example.manage.response;

import com.example.manage.pojo.User;
import lombok.Data;

import java.util.List;

@Data
public class SelectUserResponse {
    private List<UserResponse> list;
    private Long total;
}
