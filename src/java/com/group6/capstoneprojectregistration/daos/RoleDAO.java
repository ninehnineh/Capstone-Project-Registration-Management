/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.dtos.RoleDTO;
import com.group6.capstoneprojectregistration.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class RoleDAO {
    
    public static final String GET_ROLE=" SELECT Name FROM Role WHERE RoleId=?";
    
    public RoleDTO getRole(int roleId) throws SQLException{
        RoleDTO role = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtils.getConnection();
            String sql = GET_ROLE;
            stm = conn.prepareStatement(sql);
            stm.setInt(1, roleId);
            rs = stm.executeQuery();
            if (rs.next()) {
                String Name = rs.getString("Name");
                
                role = new RoleDTO(roleId, Name);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (rs != null) {
                rs.close();
            }
            if (stm !=null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        
        return role;
    }
}
