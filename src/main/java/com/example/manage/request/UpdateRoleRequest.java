package com.example.manage.request;

import lombok.Data;

@Data
public class UpdateRoleRequest {
    private Long id;

    private String roleName;

    private Short mark;

    private Short isDeleted;
}
