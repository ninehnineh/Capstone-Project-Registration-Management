/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.dtos.EventDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import com.group6.capstoneprojectregistration.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class EventDAO {

    private static final String INSERT_EVENT = " INSERT INTO [Event] (Receiver, Sender, [Event]) VALUES (?, ?, ?)";
    private static final String GET_ALL_EVENT_BY_EMAIL = " SELECT Receiver, Sender, [Event] FROM [Event] WHERE Receiver = ?";
    private static final String GET_EVENT = " SELECT Receiver, Sender, [Event] FROM [Event] WHERE Receiver = ? AND [Event] = ? AND Sender = ?";
//    private static final String GET_ALL_EVENT = " SELECT Receiver, Sender, [Event] FROM [Event]";
    private static final String CHECK_DUPLICATE = " SELECT Receiver, Sender, [Event] FROM [Event] WHERE Receiver = ? AND Sender = ? AND [Event] = ? ";
    private static final String DELETE_MESSAGE = " DELETE FROM [Event] WHERE Receiver = ? AND Sender =? AND [Event] = ?";
    private static final String DELETE_MESSAGE_BY_RECEIVER_AND_SENDER = " DELETE FROM [Event] WHERE Receiver = ? AND Sender = ?  AND [Event] = ?";
    private static final String DELETE_ALL_MESSAGE_BY_SENDER_AND_EVENT = " DELETE FROM [Event] WHERE Sender = ? AND [Event] = ?";
    private static final String DELETE_ALL_MESSAGE_BY_RECEIVER_EMAIL = " DELETE FROM [Event] WHERE Receiver = ?";

    public boolean deleteAllMessageByReceiverEmail(String receiverEmail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE_ALL_MESSAGE_BY_RECEIVER_EMAIL;
                stm = conn.prepareStatement(sql);
                stm.setString(1, receiverEmail);
                check = stm.executeUpdate() > 0 ? true : false;
            }
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

    public boolean deleteAllMessageBySenderAndEvent(String sender, String event) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE_ALL_MESSAGE_BY_SENDER_AND_EVENT;
                stm = conn.prepareStatement(sql);
                stm.setString(1, sender);
                stm.setString(2, event);
                check = stm.executeUpdate() > 0 ? true : false;
            }
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

    public boolean deleteMessageByReceiverAndSender(String receiver, String sender, String event) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE_MESSAGE_BY_RECEIVER_AND_SENDER;
                stm = conn.prepareStatement(sql);
                stm.setString(1, receiver);
                stm.setString(2, sender);
                stm.setString(3, event);
                check = stm.executeUpdate() > 0 ? true : false;
            }
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

    public boolean insertMessageOfLecturer(String sender, String receiverEmail, String event) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = INSERT_EVENT;
                stm = conn.prepareStatement(sql);
                stm.setString(1, receiverEmail);
                stm.setString(2, sender);
                stm.setString(3, event);
                check = stm.executeUpdate() > 0 ? true : false;
            }
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

    public boolean deleteMessage(String emailReceiver, UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE_MESSAGE;
                stm = conn.prepareStatement(sql);
                stm.setString(1, emailReceiver);
                stm.setString(2, user.getUserId());
                stm.setString(3, "Invite");
                check = stm.executeUpdate() > 0 ? true : false;
            }
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

    public boolean checkDuplicate() throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = CHECK_DUPLICATE;
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {

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

        return check;
    }

    public EventDTO getEventByReceiverAndEvent(String argReceiver, String sender) throws SQLException {
        EventDTO event = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_EVENT;
                stm = conn.prepareStatement(sql);
                stm.setString(1, argReceiver);
                stm.setString(2, "Invite");
                stm.setString(3, sender);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String receiver = rs.getString("Receiver");
                    String currentUser = rs.getString("Sender");
                    String getEvent = rs.getString("Event");
                    UserDAO usDao = new UserDAO();
                    MessageEventDAO meDao = new MessageEventDAO();

                    event = new EventDTO(receiver, usDao.getUserById(currentUser), meDao.getMessageContentByEvent(getEvent));
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

        return event;
    }

    public List<EventDTO> getAllEventByReceiverEmail(String userId) throws SQLException {
        List<EventDTO> listEvent = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_ALL_EVENT_BY_EMAIL;
                stm = conn.prepareStatement(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String sender = rs.getString("Sender");
                    String event = rs.getString("Event");
                    MessageEventDAO meDao = new MessageEventDAO();
                    UserDAO usDao = new UserDAO();
                    listEvent.add(new EventDTO(userId, usDao.getUserById(sender), meDao.getMessageContentByEvent(event)));
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

        return listEvent;
    }

    public boolean insertInviteEvent(String receiverEmail, UserDTO sender) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = INSERT_EVENT;
                stm = conn.prepareStatement(sql);
                stm.setString(1, receiverEmail);
                stm.setString(2, sender.getUserId());
                stm.setString(3, "Invite");
                check = stm.executeUpdate() > 0 ? true : false;
            }
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

    public boolean insertEvent(UserDTO receiver, UserDTO sender, String event) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = INSERT_EVENT;
                stm = conn.prepareStatement(sql);
                stm.setString(1, receiver.getEmail());
                stm.setString(2, sender.getUserId());
                stm.setString(3, event);
                check = stm.executeUpdate() > 0 ? true : false;
            }
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
}
