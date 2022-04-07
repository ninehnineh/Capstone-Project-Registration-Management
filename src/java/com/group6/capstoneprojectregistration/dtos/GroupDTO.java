/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author admin
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    
    private int groupId;
    private String name;
    private boolean isApproved;
    private ProjectDTO project;

    public GroupDTO(int groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }
}
