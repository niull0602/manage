package com.example.manage.service.impl;

import com.example.manage.constant.ManageConstant;
import com.example.manage.dao.RoleDao;
import com.example.manage.pojo.Role;
import com.example.manage.request.AddRoleRequest;
import com.example.manage.request.UpdateRoleRequest;
import com.example.manage.response.RoleResponseList;
import com.example.manage.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Integer insertRole(AddRoleRequest addRoleRequest) {
        Role role = new Role();
        BeanUtils.copyProperties(addRoleRequest,role);
        role.setCreateTime(Instant.now());
        role.setUpdateTime(Instant.now());
        return roleDao.insert(role);
    }

    @Override
    public Integer updateRole(UpdateRoleRequest updateRoleRequest) {
        Role role = new Role();
        BeanUtils.copyProperties(updateRoleRequest,role);
        role.setUpdateTime(Instant.now());
        return roleDao.update(role);
    }

    @Override
    public Integer deleteById(Long id) {
        Role role = new Role();
        role.setId(id);
        role.setIsDeleted(ManageConstant.IS_DELETED);
        return roleDao.update(role);
    }

    @Override
    public RoleResponseList getAll(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Role> roleList = roleDao.findAll();
        PageInfo<Role> rolePageInfo = new PageInfo<>(roleList);
        List<Role> list = rolePageInfo.getList();
        RoleResponseList roleResponseList = new RoleResponseList();
        roleResponseList.setRoleList(list);
        roleResponseList.setTotal(rolePageInfo.getTotal());
        return roleResponseList;
    }
}
