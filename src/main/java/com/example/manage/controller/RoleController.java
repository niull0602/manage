package com.example.manage.controller;

import com.example.manage.common.SzpJsonResult;
import com.example.manage.request.AddRoleRequest;
import com.example.manage.request.UpdateRoleRequest;
import com.example.manage.response.RoleResponseList;
import com.example.manage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping(value = "insert/role")
    public SzpJsonResult<Integer> insertRole(@RequestBody AddRoleRequest addRoleRequest){
        return SzpJsonResult.ok(roleService.insertRole(addRoleRequest));
    }

    @PutMapping(value = "update/role")
    public SzpJsonResult<Integer> updateRole(@RequestBody UpdateRoleRequest updateRoleRequest){
        return SzpJsonResult.ok(roleService.updateRole(updateRoleRequest));
    }

    @DeleteMapping(value = "delete/role/{id}")
    public SzpJsonResult<Integer> deleteRoleById(@PathVariable(value = "id")Long id){
        return SzpJsonResult.ok(roleService.deleteById(id));
    }

    @GetMapping(value = "get/all/role")
    public SzpJsonResult<RoleResponseList> getAll(@RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                  @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        return SzpJsonResult.ok(roleService.getAll(pageNumber,pageSize));
    }
}
