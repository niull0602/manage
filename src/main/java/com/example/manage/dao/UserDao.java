package com.example.manage.dao;

import com.example.manage.constant.ManageConstant;
import com.example.manage.mapper.UserMapper;
import com.example.manage.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Repository
public class UserDao {
    @Autowired
    private UserMapper userMapper;

    public Integer addUser(User user){
        return userMapper.insert(user);
    }

    public Integer updateUser(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectUser(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    public User login(String phoneNumber, String password) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("mark",ManageConstant.USER_MARK)
                .andEqualTo("isDeleted",ManageConstant.NOT_DELETED)
                .andEqualTo("phoneNumber",phoneNumber)
                .andEqualTo("password",password);
        return userMapper.selectOneByExample(example);
    }

    public Integer selectUserByPhoneNumber(String phoneNumber) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("phoneNumber",phoneNumber);
        return userMapper.selectByExample(example).size();
    }


    public User findByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<User> findUserByKeyWord(String keyword) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("isDeleted",ManageConstant.NOT_DELETED)
                .andLike("phoneNumber","%"+keyword+"%");
        return userMapper.selectByExample(example);
    }

    public List<User> findAll() {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("isDeleted", ManageConstant.NOT_DELETED);
        return userMapper.selectByExample(example);
    }
}
