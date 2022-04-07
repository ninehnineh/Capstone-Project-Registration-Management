/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import com.group6.capstoneprojectregistration.utils.DBUtils;
import com.sun.xml.internal.ws.org.objectweb.asm.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserDAO {

    private static final String GET_USER_BY_EMAIL = " SELECT UserId, Email, Username, Gender, Role, [Group], Isleader FROM [User] WHERE Email = ?";
    private static final String UPDATE_GROUP_USER = " UPDATE [User] SET [Group] = ?, IsLeader = ? WHERE UserId = ?";
    private static final String GET_LIST_USER_BY_GROUP_ID = " SELECT * FROM [User] WHERE [Group] = ?";
    private static final String GET_LIST_NO_GROUP_USER = " SELECT * FROM [User] WHERE [Group] is null AND [Role] = ?";
    private static final String GET_USER_BY_ID = " SELECT * FROM [User] WHERE UserId = ?";
    private static final String ADD_USER_INTO_GROUP = " UPDATE [User] SET [Group] = ? WHERE UserId = ?";
    private static final String COUNT_STUDENT_IN_GROUP = " SELECT count(*) as Students FROM [User] WHERE [Group] = ? ";
    private static final String INSERT_STUDENTS = " INSERT INTO [User] (UserId, Email, Username, Gender, Role, [Group], Isleader) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String REMOVE_STUDENT_FROM_GROUP = " UPDATE [User] SET [Group] = ? WHERE UserId = ?";
    private static final String UPDATE_GROUP_ISLEADER_BY_USER_ID = " UPDATE [USER] SET [Group] = ?, Isleader = ? WHERE UserId = ?";
    private static final String SEARCH_USER_BY_EMAIL = " SELECT * FROM [User] WHERE Email like ? AND [Group] is null AND Role = 1";
    private static final String UPDATE_GROUP_BY_USER_ID = " UPDATE [User] SET [Group] = ? WHERE UserId = ?";
    private static final String COUNT_STUDENT_NO_GROUP = " SELECT count(*) as NumOfStu FROM [User] WHERE Role = 1 AND [Group] is null";
    private static final String GET_ALL_USER_IN_GROUP = " SELECT * FROM [User] WHERE [Group] IS NOT NULL ORDER BY [Group] ASC";
    
    
    public List<UserDTO> getAllUserInGroup() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_ALL_USER_IN_GROUP;
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    String userId = rs.getString(1);
                    String email = rs.getString(2);
                    String userName = rs.getString(3);
                    String gender = rs.getString(4);
                    int role = rs.getInt(5);
                    int group = rs.getInt(6);
                    boolean isLeader = rs.getBoolean(7);
                    RoleDAO rlDao = new RoleDAO();
                    GroupDAO grDao = new GroupDAO();
                    list.add(new UserDTO(userId, email, userName, gender, rlDao.getRole(role), grDao.getGroupByGroupId(group), isLeader));
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

    public int countStudentNoGroup() throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = COUNT_STUDENT_NO_GROUP;
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int numOfStu = rs.getInt("NumOfStu");
                    count = numOfStu;
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

        return count;
    }

    public boolean updateGroupByUserId(String userId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_GROUP_BY_USER_ID;
                stm = conn.prepareStatement(sql);
                stm.setNull(1, Type.INT);
                stm.setString(2, userId);
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

    public boolean updateGroupByUserId(String userId, int groupId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_GROUP_BY_USER_ID;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupId);
                stm.setString(2, userId);
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

    public List<UserDTO> searchUserByEmail(String email) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = SEARCH_USER_BY_EMAIL;
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + email + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString("UserId");
                    String emailUser = rs.getString("Email");
                    String userName = rs.getString("Username");
                    String gender = rs.getString("Gender");
                    int role = rs.getInt("Role");
                    RoleDAO rlDao = new RoleDAO();
                    GroupDAO grDao = new GroupDAO();
                    list.add(new UserDTO(userId, emailUser, userName, gender, rlDao.getRole(role), null, false));
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

    public boolean updateGroupAndIsLeaderByUserId(String userId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_GROUP_ISLEADER_BY_USER_ID;
                stm = conn.prepareStatement(sql);
                stm.setNull(1, Type.INT);
                stm.setNull(2, Type.BOOLEAN);
                stm.setString(3, userId);
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

    public boolean removeStudentFromGroupByUserId(String userId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = REMOVE_STUDENT_FROM_GROUP;
                stm = conn.prepareStatement(sql);
                stm.setString(1, null);
                stm.setString(2, userId);
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

    public int countStudentInGroup(int group) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = COUNT_STUDENT_IN_GROUP;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, group);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int numOfStudent = rs.getInt("Students");
                    count = numOfStudent;
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

        return count;
    }

    public boolean addUserIntoGroup(UserDTO sender, String invitedUserId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = ADD_USER_INTO_GROUP;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, sender.getGroup().getGroupId());
                stm.setString(2, invitedUserId);
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

    public UserDTO getUserById(String strUserId) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_USER_BY_ID;
                stm = conn.prepareStatement(sql);
                stm.setString(1, strUserId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("Email");
                    String userName = rs.getString("UserName");
                    String gender = rs.getString("Gender");
                    int role = rs.getInt("Role");
                    int group = rs.getInt("Group");
                    boolean isLeader = rs.getBoolean("Isleader");
                    RoleDAO dao = new RoleDAO();
                    GroupDAO grdao = new GroupDAO();
                    user = new UserDTO(strUserId, email, userName, gender, dao.getRole(role), grdao.getGroupNameById(group), isLeader);
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

        return user;
    }

    public List<UserDTO> getListNoGroupUser(int intRole) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_LIST_NO_GROUP_USER;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, intRole);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString("UserId");
                    String email = rs.getString("Email");
                    String userName = rs.getString("Username");
                    String gender = rs.getString("Gender");
                    int role = rs.getInt("role");
                    int group = rs.getInt("Group");
                    boolean isLeader = rs.getBoolean("IsLeader");
                    RoleDAO rlDao = new RoleDAO();
                    GroupDAO grDao = new GroupDAO();
                    listUser.add(new UserDTO(userId, email, userName, gender, rlDao.getRole(role), grDao.getGroupNameById(group), isLeader));
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

        return listUser;
    }

    public List<UserDTO> getListUserByGroupId(int groupId) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_LIST_USER_BY_GROUP_ID;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString("UserId");
                    String email = rs.getString("Email");
                    String userName = rs.getString("UserName");
                    String gender = rs.getString("Gender");
                    int role = rs.getInt("Role");
                    boolean isLeader = rs.getBoolean("IsLeader");
                    RoleDAO rlDao = new RoleDAO();
                    GroupDAO grDao = new GroupDAO();
                    listUser.add(new UserDTO(userId, email, userName, gender, rlDao.getRole(role), grDao.getGroupNameById(groupId), isLeader));
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

        return listUser;
    }

    public UserDTO getUserByEmail(String email) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = GET_USER_BY_EMAIL;
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                String userId = rs.getString("UserId");
                String userName = rs.getString("Username");
                String gender = rs.getString("Gender");
                int role = rs.getInt("Role");
                RoleDAO dao = new RoleDAO();
                boolean isLeader = rs.getBoolean("Isleader");
                int group = rs.getInt("Group");
                GroupDAO grdao = new GroupDAO();
                user = new UserDTO(userId, email, userName, gender, dao.getRole(role), grdao.getGroupNameById(group), isLeader);
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

        return user;
    }

    public boolean updateGroupUser(UserDTO user, GroupDTO group) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_GROUP_USER;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, group.getGroupId());
                stm.setBoolean(2, true);
                stm.setString(3, user.getUserId());
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

    public boolean insert(ArrayList<String> student) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = INSERT_STUDENTS;
                stm = conn.prepareStatement(sql);

                stm.setString(1, student.get(1));
                stm.setString(2, student.get(2));
                stm.setString(3, student.get(3));
                stm.setString(4, student.get(4));

                stm.setInt(5, 1); //student 1
                stm.setNull(6, Types.INTEGER);
                stm.setBoolean(7, false);
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
    public boolean insertMentor(ArrayList<String> mentor) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = INSERT_STUDENTS;
                stm = conn.prepareStatement(sql);

                stm.setString(1, mentor.get(1));
                stm.setString(2, mentor.get(2));
                stm.setString(3, mentor.get(3));
                stm.setString(4, mentor.get(4));

                stm.setInt(5, 2); //mentor 2
                stm.setNull(6, Types.INTEGER);
                stm.setBoolean(7, false);
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
    public boolean updateGroupFromExcel(String userId, GroupDTO group, boolean isLeader) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_GROUP_USER;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, group.getGroupId());
                stm.setBoolean(2, isLeader);
                stm.setString(3, userId);
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
