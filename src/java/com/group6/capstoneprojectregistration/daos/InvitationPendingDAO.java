/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.controllers.InviteUserController;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.InvitationPendingDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import com.group6.capstoneprojectregistration.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class InvitationPendingDAO {

    private static final String INSERT_PENDING_USER = " INSERT INTO [Invitation Pending] ([Status], [User], [Group], Userinvited) VALUES (?, ?, ?, ?)";
    private static final String GET_USER_PENDING_BY_INVITED_USER = " SELECT Status, [User], [Group], UserInvited FROM [Invitation Pending] WHERE UserInvited = ? ";
    private static final String GET_USER_PENDING = " SELECT Status, [User], [Group], UserInvited FROM [Invitation Pending] WHERE [User] = ? AND Status = ?";
    private static final String UPDATE_STATUS_ACCEPT = " UPDATE [Invitation Pending] SET Status = ? WHERE [User] = ? AND UserInvited =?";
    private static final String GET_USER_PENDING_BY_EMAIL = " SELECT * FROM [Invitation Pending] WHERE UserInvited = ?";
    private static final String DELETE_USER_PENDING_BY_USER_INVITED = " DELETE FROM [Invitation Pending] WHERE UserInvited = ? AND [User] = ?";
    private static final String DELETE_ALL_USER_PENDING_BY_USER = " DELETE FROM [Invitation Pending] WHERE [User] = ?";
    private static final String GET_INVITATION_BY_USER = " SELECT ip.UserInvited\n"
            + "FROM [User] u join [Invitation Pending] ip \n"
            + "on u.UserId = ip.[User]\n"
            + "Where u.UserId = ? ";
    private static final String GET_CURRENT_INVITATION_PENDING = " SELECT ip.UserInvited\n"
            + "FROM [User] u join [Invitation Pending] ip\n"
            + "on u.UserId = ip.[User]";
    private static final String DELETE_ALL_USER_PENDING = " DELETE FROM [Invitation Pending] WHERE [Group] = ?";

    public boolean deleteAllUserPending(int groupId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE_ALL_USER_PENDING;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupId);
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

    public List<InvitationPendingDTO> getCurrentInvitationPending() throws SQLException {
        List<InvitationPendingDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_CURRENT_INVITATION_PENDING;
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userInvited = rs.getString("UserInvited");
                    list.add(new InvitationPendingDTO(userInvited));
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

        return list;
    }

    public List<InvitationPendingDTO> getInvitationByUser(String userId) throws SQLException {
        List<InvitationPendingDTO> invi = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_INVITATION_BY_USER;
                stm = conn.prepareStatement(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userInvited = rs.getString("UserInvited");
                    invi.add(new InvitationPendingDTO(userInvited));
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

        return invi;
    }

    public boolean deleteAllUserPendingByUser(String user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE_ALL_USER_PENDING_BY_USER;
                stm = conn.prepareStatement(sql);
                stm.setString(1, user);
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

    public boolean deleteUserPendingByUserInvitedAndLeaderId(String userInvited, String leaderId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE_USER_PENDING_BY_USER_INVITED;
                stm = conn.prepareStatement(sql);
                stm.setString(1, userInvited);
                stm.setString(2, leaderId);
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

    public boolean insertPendingUser(UserDTO user, GroupDTO group, String receiver) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = INSERT_PENDING_USER;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, 1);
                stm.setString(2, user.getUserId());
                stm.setInt(3, group.getGroupId());
                stm.setString(4, receiver);
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

    public List<InvitationPendingDTO> getUserPending(String receiverEmail) throws SQLException {
        List<InvitationPendingDTO> listUserPending = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_USER_PENDING_BY_INVITED_USER;
                stm = conn.prepareStatement(sql);
                stm.setString(1, receiverEmail);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int status = rs.getInt("Status");
                    String user = rs.getString("User");
                    int group = rs.getInt("Group");
                    UserStatusDAO ustDao = new UserStatusDAO();
                    UserDAO usDao = new UserDAO();
                    GroupDAO grDao = new GroupDAO();
                    listUserPending.add(new InvitationPendingDTO(ustDao.getStatusById(status), usDao.getUserById(user), grDao.getGroupNameById(group), receiverEmail));
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

        return listUserPending;
    }

    public List<InvitationPendingDTO> getUserPedingByLoginUserAndStatus(String userID, int statusId) throws SQLException {
        List<InvitationPendingDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_USER_PENDING;
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setInt(2, statusId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int status = rs.getInt("Status");
                    String user = rs.getString("User");
                    int group = rs.getInt("Group");
                    String userInvited = rs.getString("UserInvited");
                    UserStatusDAO ustDao = new UserStatusDAO();
                    UserDAO usDao = new UserDAO();
                    GroupDAO grDao = new GroupDAO();
                    list.add(new InvitationPendingDTO(ustDao.getStatusById(status), usDao.getUserById(user), grDao.getGroupNameById(group), userInvited));

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

        return list;
    }

    public InvitationPendingDTO getUserPendingByEmail(String emailReceiver, String sender) throws SQLException {
        InvitationPendingDTO invite = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_USER_PENDING_BY_EMAIL;
                stm = conn.prepareStatement(sql);
                stm.setString(1, emailReceiver);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int status = rs.getInt("Status");
                    int group = rs.getInt("Group");
                    String userInvited = rs.getString("UserInvited");
                    UserStatusDAO ussDao = new UserStatusDAO();
                    GroupDAO grDao = new GroupDAO();
                    UserDAO usDao = new UserDAO();
                    invite = new InvitationPendingDTO(ussDao.getStatusById(status), usDao.getUserById(sender), grDao.getGroupNameById(group), userInvited);
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

        return invite;
    }

    public boolean updateStatus(InvitationPendingDTO invi, int status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_STATUS_ACCEPT;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setString(2, invi.getUser().getUserId());
                stm.setString(3, invi.getUserInvited());
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
