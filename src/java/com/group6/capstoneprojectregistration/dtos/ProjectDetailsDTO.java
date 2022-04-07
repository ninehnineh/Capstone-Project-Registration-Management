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
public class ProjectDetailsDTO implements Serializable{
    
    private int id;
    private ProjectDTO project;
    private GroupDTO group;

    public ProjectDetailsDTO(ProjectDTO project, GroupDTO group) {
        this.project = project;
        this.group = group;
    }
    
}
