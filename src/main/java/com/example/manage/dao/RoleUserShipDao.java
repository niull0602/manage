package com.example.manage.dao;

import com.example.manage.mapper.RoleUserShipMapper;
import com.example.manage.pojo.RoleUserShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

@Repository
public class RoleUserShipDao {
    @Autowired
    private RoleUserShipMapper roleUserShipMapper;

    public RoleUserShip getRoleUserShipByUserId(Long userId){
        Example example = new Example(RoleUserShip.class);
        example.createCriteria()
                .andEqualTo("userId",userId);
        return roleUserShipMapper.selectOneByExample(example);
    }

    public Integer insert(RoleUserShip roleUserShip) {
        return roleUserShipMapper.insert(roleUserShip);
    }


    public Integer update(Long roleId, Long newUserId) {
        Example example = new Example(RoleUserShip.class);
        example.createCriteria()
                .andEqualTo("userId",newUserId);
        RoleUserShip roleUserShip = roleUserShipMapper.selectOneByExample(example);
        roleUserShip.setRoleId(roleId);
        return roleUserShipMapper.updateByPrimaryKey(roleUserShip);
    }
}
