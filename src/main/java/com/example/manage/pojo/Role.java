package com.example.manage.pojo;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "t_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name",length = 20)
    private String roleName;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;
    /**
     * 未激活：0 激活：1
     */
    @Column(columnDefinition = "smallint comment '未激活-0  激活-1'")
    private Short mark;
    /**
     * 未删除：0 删除：1
     */
    @Column(name = "is_deleted")
    private Short isDeleted;




}