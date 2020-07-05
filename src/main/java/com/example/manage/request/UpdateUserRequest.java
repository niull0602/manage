package com.example.manage.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private Long id;

    private String phoneNumber;

    private String password;

    private String nickName;
    @ApiModelProperty(value="未激活：0 激活：1",example = "1")
    private Short mark;
    @ApiModelProperty(value="未删除：0 删除：1",example = "0")
    private Short isDeleted;

    private Long roleId;
}
