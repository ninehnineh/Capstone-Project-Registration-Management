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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO implements Serializable{
    
    private String projectId;
    private String name;
    private UserDTO mentor;
    private String coMentor;
    private int numOfStus;
    private boolean isSelected;
    private String discription;
    private SemesterDTO semester;

    public ProjectDTO(String projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }

    
}
