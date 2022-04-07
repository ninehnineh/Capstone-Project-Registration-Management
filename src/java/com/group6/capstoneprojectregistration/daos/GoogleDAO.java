/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.dtos.GoogleDTO;
import com.group6.capstoneprojectregistration.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class GoogleDAO {

    public static final String REGISTER_GOOGLE = " INSERT INTO tblUsers (ID ,Email, Verified_email, Picture) VALUES(?,?,?,?)";
    public static final String CHECK_GOOGLE = " SELECT * FROM tblUsers WHERE email=?";

    public boolean register(String id, String email, boolean verified_email, String picture) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql = REGISTER_GOOGLE;
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, email);
            stm.setBoolean(3, true);
            stm.setString(4, picture);
            check = stm.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public GoogleDTO checklogin(String email) throws SQLException {

        GoogleDTO gg = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = CHECK_GOOGLE;
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                String id = rs.getString("ID");
                boolean verified_email = rs.getBoolean("Verified_email");
                String name = rs.getString("Name");
                String given_name = rs.getString("given_name");
                String family_name = rs.getString("family_name");
                String link = rs.getString("link");
                String picture = rs.getString("picture");
                
                gg = new GoogleDTO(id, email, verified_email, name, given_name, family_name,link, picture);
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
        return gg;
    }
}
