package com.example.manage.controller;

import com.example.manage.common.SzpJsonResult;
import com.example.manage.request.AddUserRequest;
import com.example.manage.request.UpdateUserRequest;
import com.example.manage.response.LoginResponse;
import com.example.manage.response.SelectUserResponse;
import com.example.manage.response.UserResponse;
import com.example.manage.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "login")
    public SzpJsonResult<LoginResponse> login(@RequestParam String phoneNumber, @RequestParam String password) throws Exception {
        LoginResponse login = userService.login(phoneNumber, password);
        if (login.getToken()!=null)
        return SzpJsonResult.ok(login);
        else
            return new SzpJsonResult(401,"账号或密码错误",null);
    }
    @PostMapping(value = "add/user")
    public SzpJsonResult<Integer> addUser(@RequestBody AddUserRequest addUserRequest){
        return SzpJsonResult.ok(userService.addUser(addUserRequest));
    }
    @DeleteMapping(value = "delete/user/{userId}")
    public SzpJsonResult<Integer> deleteUser(@PathVariable(value = "userId") Long userId){
        return SzpJsonResult.ok(userService.deleteUser(userId));
    }
    @PutMapping(value = "update/user")
    public SzpJsonResult<Integer> updateUser(@RequestBody UpdateUserRequest updateUserRequest){
        return SzpJsonResult.ok(userService.updateUser(updateUserRequest));
    }

    @GetMapping(value = "select/user/by/id")
    public SzpJsonResult<UserResponse> selectUser(@RequestParam Long userId){
        return SzpJsonResult.ok(userService.selectUser(userId));
    }
    @ApiOperation("判断用户是否存在")
    @GetMapping(value = "exits/user")
    public SzpJsonResult<Integer> exitsUser(@RequestParam(value = "phoneNumber")String phoneNumber){
        return SzpJsonResult.ok(userService.exits(phoneNumber));
    }
    @ApiOperation("关键字查询")
    @GetMapping(value = "find/user/keyword")
    public SzpJsonResult<SelectUserResponse> findUserByKeywords(@RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                                                                @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                                @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        return SzpJsonResult.ok(userService.findUserByKeywords(keyword,pageNumber,pageSize));
    }
    @ApiOperation("查询全部")
    @GetMapping(value = "find/all/user")
    public SzpJsonResult<SelectUserResponse> findAllUser(@RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                         @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        return SzpJsonResult.ok(userService.findAllUser(pageNumber,pageSize));
    }

}
