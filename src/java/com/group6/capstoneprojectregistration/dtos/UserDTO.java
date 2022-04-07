/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.dtos;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author admin
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDTO implements Serializable{

    private String userId;
    private String email;
    private String userName;
    private String gender;
    private RoleDTO role;
    private GroupDTO group;
    private boolean isLeader;

    public UserDTO() {
        this.userId = "";
        this.email = "";
        this.userName = "";
        this.gender = "";
        this.role = null;
        this.group = null;
        this.isLeader = false;
    }

    
}
