package com.example.manage.pojo;

import lombok.*;

import javax.persistence.*;


@Table(name = "t_role_user_ship")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleUserShip {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;



}
