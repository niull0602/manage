package com.example.manage.response;

import com.example.manage.pojo.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleResponseList {
    private List<Role> roleList;
    private Long total;
}
