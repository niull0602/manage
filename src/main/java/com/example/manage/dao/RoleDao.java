package com.example.manage.dao;

import com.example.manage.constant.ManageConstant;
import com.example.manage.mapper.RoleMapper;
import com.example.manage.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class RoleDao {
    @Autowired
    private RoleMapper roleMapper;

    public Role getRoleById(Long roleId){
        return roleMapper.selectByPrimaryKey(roleId);
    }

    public Integer insert(Role role) {
        return roleMapper.insert(role);
    }

    public Integer update(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    public List<Role> findAll() {
        Example example = new Example(Role.class);
        example.createCriteria()
                .andEqualTo("isDeleted", ManageConstant.NOT_DELETED);
        return roleMapper.selectByExample(example);
    }
}
