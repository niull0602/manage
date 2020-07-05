package com.example.manage.request;

import lombok.Data;

@Data
public class AddRoleRequest {
    private String roleName;

    private Short mark=1;

    private Short isDeleted=0;
}
