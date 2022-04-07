/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.dtos.UserStatusDTO;
import com.group6.capstoneprojectregistration.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class UserStatusDAO {

    private static final String GET_STATUS_BY_ID = " SELECT Status FROM [User Status] WHERE StatusId = ?";

    public UserStatusDTO getStatusById(int statusId) throws SQLException {
        UserStatusDTO userStatus = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
               String sql = GET_STATUS_BY_ID;
               stm = conn.prepareStatement(sql);
               stm.setInt(1, statusId);
               rs = stm.executeQuery();
                if (rs.next()) {
                    String status = rs.getString("Status");
                    userStatus = new UserStatusDTO(statusId, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return userStatus;
    }
}
