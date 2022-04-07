/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.dtos.MessageEventDTO;
import com.group6.capstoneprojectregistration.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class MessageEventDAO {

    private static final String GET_MESS_CONTENT_BY_EVENT = " SELECT MessageContent, MessageEvent FROM [Message Event] WHERE MessageEvent = ? ";
    private static final String GET = " ";

    public MessageEventDTO get() throws SQLException {
        MessageEventDTO message = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

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

        return message;
    }

    public MessageEventDTO getMessageContentByEvent(String messEvent) throws SQLException {
        MessageEventDTO mesEvent = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_MESS_CONTENT_BY_EVENT;
                stm = conn.prepareStatement(sql);
                stm.setString(1, messEvent);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String messageEvent = rs.getString("MessageEvent");
                    String messageContent = rs.getString("MessageContent");

                    mesEvent = new MessageEventDTO(messageEvent, messageContent);
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

        return mesEvent;
    }
}
