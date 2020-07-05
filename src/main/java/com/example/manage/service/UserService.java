package com.example.manage.service;


import com.example.manage.request.AddUserRequest;
import com.example.manage.request.UpdateUserRequest;
import com.example.manage.response.LoginResponse;
import com.example.manage.response.SelectUserResponse;
import com.example.manage.response.UserResponse;

public interface UserService {

    Integer addUser(AddUserRequest addUserRequest);

    Integer deleteUser(Long userId);

    Integer updateUser(UpdateUserRequest updataUserRequest);

    UserResponse selectUser(Long userId);

    LoginResponse login(String phoneNumber, String password);

    Integer exits(String phoneNumber);

    SelectUserResponse findUserByKeywords(String keyword, Integer pageNumber, Integer pageSize);

    SelectUserResponse findAllUser(Integer pageNumber, Integer pageSize);
}
