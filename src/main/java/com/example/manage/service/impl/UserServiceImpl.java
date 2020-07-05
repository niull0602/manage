package com.example.manage.service.impl;

import com.example.manage.constant.ManageConstant;
import com.example.manage.dao.RoleDao;
import com.example.manage.dao.RoleUserShipDao;
import com.example.manage.dao.UserDao;
import com.example.manage.mapper.RoleMapper;
import com.example.manage.mapper.RoleUserShipMapper;
import com.example.manage.pojo.Role;
import com.example.manage.pojo.RoleUserShip;
import com.example.manage.pojo.User;
import com.example.manage.request.AddUserRequest;
import com.example.manage.request.UpdateUserRequest;
import com.example.manage.response.LoginResponse;
import com.example.manage.response.SelectUserResponse;
import com.example.manage.response.UserResponse;
import com.example.manage.service.UserService;
import com.example.manage.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleUserShipDao roleUserShipDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public LoginResponse login(String phoneNumber, String password){
        LoginResponse loginResponse = new LoginResponse();
        User user = userDao.login(phoneNumber, password);
        if (user != null) {
            BeanUtils.copyProperties(user,loginResponse);
            RoleUserShip roleUserShipByUserId = roleUserShipDao.getRoleUserShipByUserId(user.getId());
            Role role = roleDao.getRoleById(roleUserShipByUserId.getRoleId());
            loginResponse.setRoleName(role.getRoleName());
            String uuid = UUID.randomUUID().toString();
            String token = uuid + "," + user.getId() + "," + user.getNickName() + "," + user.getPhoneNumber();
            //将token留在redis中
            stringRedisTemplate.opsForValue().set(token, JsonUtils.objectToJson(user));
            stringRedisTemplate.expire(token, 10, TimeUnit.DAYS);
            loginResponse.setToken(token);
        }
        return loginResponse;
    }

    @Override
    public Integer addUser(AddUserRequest addUserRequest) {
        User user = new User();
        BeanUtils.copyProperties(addUserRequest, user);
        user.setCreateTime(Instant.now());
        user.setUpdateTime(Instant.now());
        userDao.addUser(user);
        RoleUserShip roleUserShip = new RoleUserShip();
        roleUserShip.setRoleId(addUserRequest.getRoleId());
        roleUserShip.setUserId(user.getId());
        return roleUserShipDao.insert(roleUserShip);
    }

    @Override
    public Integer deleteUser(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setIsDeleted(ManageConstant.IS_DELETED);
        return userDao.updateUser(user);
    }

    @Override
    public Integer updateUser(UpdateUserRequest updataUserRequest) {
        User user = new User();
        BeanUtils.copyProperties(updataUserRequest, user);
        user.setUpdateTime(Instant.ofEpochSecond((Instant.now().getEpochSecond() - 25 * 60 * 60)));
        roleUserShipDao.update(updataUserRequest.getRoleId(),updataUserRequest.getId());
        return userDao.updateUser(user);
    }

    @Override
    public UserResponse selectUser(Long userId) {
        UserResponse userResponse = new UserResponse();
        User user = userDao.selectUser(userId);
        BeanUtils.copyProperties(user, userResponse);
        RoleUserShip roleUserShipByUserId = roleUserShipDao.getRoleUserShipByUserId(user.getId());
        Role role = roleDao.getRoleById(roleUserShipByUserId.getRoleId());
        userResponse.setRoleName(role.getRoleName());
        return userResponse;
    }

    @Override
    public Integer exits(String phoneNumber) {
        return userDao.selectUserByPhoneNumber(phoneNumber);
    }

    @Override
    public SelectUserResponse findUserByKeywords(String keyword, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<User> userList  = null;
        if (!StringUtils.isEmpty(keyword)) {
            userList = userDao.findUserByKeyWord(keyword);
        }else {
            userList = userDao.findAll();
        }

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        List<User> list = pageInfo.getList();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user:list){
            UserResponse userResponse = new UserResponse();
            RoleUserShip roleShip = roleUserShipDao.getRoleUserShipByUserId(user.getId());
            BeanUtils.copyProperties(user,userResponse);
            userResponse.setRoleName(roleDao.getRoleById(roleShip.getRoleId()).getRoleName());
            userResponses.add(userResponse);
        }
        SelectUserResponse selectUserResponse = new SelectUserResponse();
        selectUserResponse.setList(userResponses);
        selectUserResponse.setTotal(pageInfo.getTotal());
        return selectUserResponse;
    }

    @Override
    public SelectUserResponse findAllUser(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<User> userList = userDao.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        List<User> list = pageInfo.getList();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user:list){
            UserResponse userResponse = new UserResponse();
            RoleUserShip roleShip = roleUserShipDao.getRoleUserShipByUserId(user.getId());
            BeanUtils.copyProperties(user,userResponse);
            userResponse.setRoleName(roleDao.getRoleById(roleShip.getRoleId()).getRoleName());
            userResponses.add(userResponse);
        }
        SelectUserResponse selectUserResponse = new SelectUserResponse();
        selectUserResponse.setList(userResponses);
        selectUserResponse.setTotal(pageInfo.getTotal());
        return selectUserResponse;
    }
}
