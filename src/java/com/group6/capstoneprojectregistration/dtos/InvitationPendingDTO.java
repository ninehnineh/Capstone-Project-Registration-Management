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

/**
 *
 * @author admin
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvitationPendingDTO implements Serializable{

    private int id;
    private UserStatusDTO status;
    private UserDTO user;
    private GroupDTO group;
    private String userInvited;

    public InvitationPendingDTO(UserDTO user, String userInvited) {
        this.user = user;
        this.userInvited = userInvited;
    }

    public InvitationPendingDTO(String userInvited) {
        this.userInvited = userInvited;
    }

    public InvitationPendingDTO(UserStatusDTO status, UserDTO user, GroupDTO group, String userInvited) {
        this.status = status;
        this.user = user;
        this.group = group;
        this.userInvited = userInvited;
    }
    

}
