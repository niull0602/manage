package com.example.manage.service;

import com.example.manage.request.AddRoleRequest;
import com.example.manage.request.UpdateRoleRequest;
import com.example.manage.response.RoleResponseList;

public interface RoleService {
    Integer insertRole(AddRoleRequest addRoleRequest);

    Integer updateRole(UpdateRoleRequest updateRoleRequest);

    Integer deleteById(Long id);

    RoleResponseList getAll(Integer pageNumber, Integer pageSize);


}
