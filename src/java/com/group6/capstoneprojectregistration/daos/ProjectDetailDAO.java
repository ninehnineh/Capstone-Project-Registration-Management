/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.ProjectDetailsDTO;
import com.group6.capstoneprojectregistration.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ProjectDetailDAO {

    private static final String INSERT_PROJECT = " INSERT INTO ProjectDetail (ProjectId,GroupId) VALUES (?,?) ";
    private static final String GET_PROJECT = " SELECT ProjectId,GroupId FROM ProjectDetail WHERE ProjectId=? AND GroupId=? ";
    private static final String GET_LIST_PROJECT_PENDING = " SELECT ProjectId,GroupId FROM ProjectDetail WHERE ProjectId=? AND GroupId=? ";
    private static final String GET_LIST_PROJECT_GUIDING = " SElect p.ProjectId, g.GroupId, MentorId, IsSelected,g.Name, g.IsApproved from ProjectDetail pjd inner join Project p  on p.ProjectId=pjd.ProjectId left join [Group] g on g.GroupId = pjd.GroupId where MentorId = ? AND g.IsApproved = 1 AND p.IsSelected = 1 ";
    private static final String GET_PROJECTDETAILS_BY_MENTORID = " SElect p.ProjectId, g.GroupId, MentorId, IsSelected\n"
            + " FROM ProjectDetail pjd inner join Project p  on p.ProjectId = pjd.ProjectId left join [Group] g on g.GroupId = pjd.GroupId\n"
            + " WHERE MentorId = ? AND IsSelected = 0  AND IsApproved = 0 "
            + " ORDER BY p.ProjectId ASC ";
    private static final String GET_PROJECT_PENDING_BY_USERID = " SELECT g.GroupId, pd.ProjectId, u.UserId\n"
            + " FROM [User] u  join [Group] g on u.[Group] = g.GroupId  join ProjectDetail pd on g.GroupId = pd.GroupId\n"
            + " Where u.UserId = ?";
    private static final String UPDATE_DENY_PROJECT = " DELETE FROM ProjectDetail WHERE ProjectId = ? AND GroupId = ?";
    private static final String GET_PROJECT_DETAIL_BY_GROUP_ID = " SELECT * FROM ProjectDetail WHERE GroupId = ?";
    private static final String DELETE_ALL_PROJECT_PENDING_BY_GROUP_ID = " DELETE FROM ProjectDetail WHERE GroupId = ?";
    private static final String DELETE_PROJECT_REGISTED_OF_ANOTHER_GROUP = " DELETE FROM ProjectDetail WHERE ProjectId = ?";
    private static final String GET_ALL_PROJECT_DETAILS = " SELECT * FROM ProjectDetail WHERE ProjectId = ?";
    private static final String DELETE_PROJECT_DETAIL_BY_PROJECT_ID_AND_GROUP_ID = " DELETE FROM ProjectDetail WHERE GroupId = ? AND ProjectId =?";
    private static final String GET_ALL_PROJECT_DETAIL_BY_GROUP_ID = " SELECT * FROM ProjectDetail WHERE GroupId = ?";
    
    
    public List<ProjectDetailsDTO> getAllProjectDetailByGroupId(int groupId) throws SQLException {
        List<ProjectDetailsDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_ALL_PROJECT_DETAIL_BY_GROUP_ID;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupId);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    int id = rs.getInt(1);
                    String projectId = rs.getString(2);
                    ProjectDAO pDao = new ProjectDAO();
                    GroupDAO grDao = new GroupDAO();
                    list.add(new ProjectDetailsDTO(id, pDao.getProjectById(projectId), grDao.getGroupByGroupId(groupId)));
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

    public boolean deleteProjectDetailByProjectIdAndGroupId(int groupId, String projectId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE_PROJECT_DETAIL_BY_PROJECT_ID_AND_GROUP_ID;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupId);
                stm.setString(2, projectId);
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

    public List<ProjectDetailsDTO> getAllProjectDetails(String projectId) throws SQLException {
        List<ProjectDetailsDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_ALL_PROJECT_DETAILS;
                stm = conn.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String projectID = rs.getString(2);
                    int groupId = rs.getInt(3);
                    ProjectDAO prDao = new ProjectDAO();
                    GroupDAO grDao = new GroupDAO();
                    list.add(new ProjectDetailsDTO(id, prDao.getProjectById(projectId), grDao.getGroupByGroupId(groupId)));
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

    public boolean deleteProjectRegistedOfAnotherGroup(String projectId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE_PROJECT_REGISTED_OF_ANOTHER_GROUP;
                stm = conn.prepareStatement(sql);
                stm.setString(1, projectId);
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

    public boolean deleteAllProjectPendingByGroupId(int groupId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE_ALL_PROJECT_PENDING_BY_GROUP_ID;
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

    public ProjectDetailsDTO getProjectDetailByGroupId(int groupId) throws SQLException {
        ProjectDetailsDTO projectDetail = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_PROJECT_DETAIL_BY_GROUP_ID;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    ProjectDAO prDao = new ProjectDAO();
                    GroupDAO grDao = new GroupDAO();
                    projectDetail = new ProjectDetailsDTO(prDao.getProjectById(projectId), grDao.getGroupByGroupId(groupId));
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

        return projectDetail;
    }

    public boolean denyProject(String projectId, int groupId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_DENY_PROJECT;
                stm = conn.prepareStatement(sql);
                stm.setString(1, projectId);
                stm.setInt(2, groupId);
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

    public List<ProjectDetailsDTO> getProjectPendingByUserId(String userId) throws SQLException {
        List<ProjectDetailsDTO> listProjectPending = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_PROJECT_PENDING_BY_USERID;
                stm = conn.prepareStatement(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int groupId = rs.getInt("GroupId");
                    String projectId = rs.getString("ProjectId");
                    GroupDAO grDao = new GroupDAO();
                    ProjectDAO prDao = new ProjectDAO();
                    listProjectPending.add(new ProjectDetailsDTO(prDao.getProjectById(projectId), grDao.getGroupByGroupId(groupId)));
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

        return listProjectPending;
    }

    public boolean insertProjectId(String projectId, int groupId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = INSERT_PROJECT;
                stm = conn.prepareStatement(sql);
                stm.setString(1, projectId);
                stm.setInt(2, groupId);
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

    public ProjectDetailsDTO getProjectDetailByGroupIdAndProjectId(int groupId, String projectId) throws SQLException {
        ProjectDetailsDTO projectDetail = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_PROJECT;
                stm = conn.prepareStatement(sql);
                stm.setString(1, projectId);
                stm.setInt(2, groupId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    ProjectDAO pdao = new ProjectDAO();
                    GroupDAO gdao = new GroupDAO();
                    projectDetail = new ProjectDetailsDTO(pdao.getProjectById(projectId), gdao.getGroupNameById(groupId));
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
        return projectDetail;
    }

    public List<ProjectDetailsDTO> getProjectDetailsByMentorId(String mentorId) throws SQLException {
        List<ProjectDetailsDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_PROJECTDETAILS_BY_MENTORID;
                stm = conn.prepareStatement(sql);
                stm.setString(1, mentorId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    int groupId = rs.getInt("GroupId");
                    String mentorID = rs.getString("MentorId");
                    ProjectDAO prjDao = new ProjectDAO();
                    GroupDAO grDao = new GroupDAO();
                    list.add(new ProjectDetailsDTO(prjDao.getProjectById(projectId), grDao.getGroupNameById(groupId)));
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

}
