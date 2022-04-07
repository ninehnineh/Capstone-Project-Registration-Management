/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.dtos.ProjectDTO;
import com.group6.capstoneprojectregistration.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author admin
 */
public class ProjectDAO {

    private static final String GET_LIST_PROJECT = " SELECT * FROM Project";
    private static final String GET_PROJECT_BY_NAME = " SELECT * FROM Project WHERE Name = ?";
    private static final String GET_PROJECT_BY_ID = " SELECT * FROM Project WHERE ProjectId = ?";
    private static final String GET_TOTAL_PROJECT = " SELECT count(*) FROM Project";
    private static final String UPDATE_PROJECT = " UPDATE Project SET IsSelected = ? WHERE ProjectId =?";
    private static final String GET_LIST_BY_MENTOR = " SELECT * FROM Project WHERE MentorId = ?";
    private static final String GET_PAGING_PROJECT = " SELECT * FROM Project WHERE IsSelected = 0 "
            + " ORDER BY ProjectId "
            + " OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY ";
    private static final String GET_PROJECT_BY_MENTOR_ID = " Select * \n"
            + "from Project p join [Group] g\n"
            + "on p.ProjectId = g.ProjectId\n"
            + "Where MentorId = ? "
            + "ORDER BY p.ProjectId ASC ";
//            
    private static final String GET_ALL_NOT_SELECTED_PROJECT = " SELECT * FROM Project WHERE IsSelected =? AND NumOfStus = 4";
    private static final String GET_PAGING_ADMIN_PROJECT = " SELECT * FROM Project WHERE IsSelected IS NOT NULL "
            + " ORDER BY ProjectId "
            + " OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY ";
    private static final String UPDATE_PROJECT_DISCRIPTION = " UPDATE Project SET Discription = ? WHERE ProjectId =?";
    private static final String INSERT_PROJECT = " INSERT INTO Project (ProjectId, Name, MentorId, [Co-Mentor], NumOfStus, IsSelected, Discription, Semester) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    public List<ProjectDTO> pagingAdminProject(int index) throws SQLException {
        List<ProjectDTO> list = new ArrayList<>();
        SemesterDAO dao = new SemesterDAO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                stm = conn.prepareStatement(GET_PAGING_ADMIN_PROJECT);
                stm.setInt(1, (index - 1) * 20);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String name = rs.getString("Name");
                    String mentor = rs.getString("MentorId");
                    String coMentor = rs.getString("Co-Mentor");
                    int num = rs.getInt("NumOfStus");
                    boolean isSelected = rs.getBoolean("IsSelected");
                    String discription = rs.getString("Discription");
                    int semester = rs.getInt("Semester");
                    UserDAO usDao = new UserDAO();
                    list.add(new ProjectDTO(projectId, name, usDao.getUserById(mentor), coMentor, num, isSelected,
                            discription, dao.getSemesterById(semester)));
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

    public List<ProjectDTO> getRandomProject(List<ProjectDTO> list, int totalItems) {
        Random rand = new Random();

        List<ProjectDTO> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {
            int randomIndex = rand.nextInt(list.size());

            newList.add(list.get(randomIndex));

            list.remove(randomIndex);

        }

        return newList;
    }

    public List<ProjectDTO> getAllNotSelectedProject(boolean isSelected) throws SQLException {
        List<ProjectDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_ALL_NOT_SELECTED_PROJECT;
                stm = conn.prepareStatement(sql);
                stm.setBoolean(1, isSelected);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String projectId = rs.getString(1);
                    String name = rs.getString(2);
                    String mentorId = rs.getString(3);
                    String coMentor = rs.getString(4);
                    int numofStu = rs.getInt(5);
                    String discription = rs.getString(7);
                    int semester = rs.getInt(8);
                    UserDAO usDao = new UserDAO();
                    SemesterDAO smDao = new SemesterDAO();
                    list.add(new ProjectDTO(projectId, name, usDao.getUserById(mentorId), coMentor, numofStu, isSelected, discription, smDao.getSemesterById(semester)));
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

    public List<ProjectDTO> getProjectByMentorId(String mentorId) throws SQLException {
        List<ProjectDTO> project = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_PROJECT_BY_MENTOR_ID;
                stm = conn.prepareStatement(sql);
                stm.setString(1, mentorId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getString("Name");
                    project.add(new ProjectDTO(projectId, projectName));
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

        return project;
    }

    public ProjectDTO getProject(String projectName) throws SQLException {
        ProjectDTO project = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_PROJECT_BY_NAME;
                stm = conn.prepareStatement(sql);
                stm.setString(1, projectName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectMentor = rs.getString("MentorId");
                    String projectCoMentor = rs.getString("Co-Mentor");
                    int projectNumOfStudent = rs.getInt("NumOfStus");
                    boolean isSelected = rs.getBoolean("IsSelected");
                    String discription = rs.getString("Discription");
                    int semester = rs.getInt("semester");
                    SemesterDAO seDao = new SemesterDAO();
                    UserDAO usDao = new UserDAO();
                    project = new ProjectDTO(projectId, projectName, usDao.getUserById(projectMentor), projectCoMentor, projectNumOfStudent, isSelected, discription, seDao.getSemesterById(semester));
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

        return project;
    }

    public List<ProjectDTO> getListProject() throws SQLException {
        List<ProjectDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            SemesterDAO dao = new SemesterDAO();
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_LIST_PROJECT;
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String name = rs.getString("Name");
                    String mentor = rs.getString("MentorId");
                    String coMentor = rs.getString("Co-Mentor");
                    int num = rs.getInt("NumOfStus");
                    boolean isSelected = rs.getBoolean("IsSelected");
                    String discription = rs.getString("Discription");
                    int semester = rs.getInt("Semester");
                    UserDAO usDao = new UserDAO();
                    list.add(new ProjectDTO(projectId, name, usDao.getUserById(mentor), coMentor, num, isSelected,
                            discription, dao.getSemesterById(semester)));
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

    public int getTotalProject() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(GET_TOTAL_PROJECT);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<ProjectDTO> pagingProject(int index) throws SQLException {
        List<ProjectDTO> list = new ArrayList<>();
        SemesterDAO dao = new SemesterDAO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                stm = conn.prepareStatement(GET_PAGING_PROJECT);
                stm.setInt(1, (index - 1) * 20);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String name = rs.getString("Name");
                    String mentor = rs.getString("MentorId");
                    String coMentor = rs.getString("Co-Mentor");
                    int num = rs.getInt("NumOfStus");
                    boolean isSelected = rs.getBoolean("IsSelected");
                    String discription = rs.getString("Discription");
                    int semester = rs.getInt("Semester");
                    UserDAO usDao = new UserDAO();
                    list.add(new ProjectDTO(projectId, name, usDao.getUserById(mentor), coMentor, num, isSelected,
                            discription, dao.getSemesterById(semester)));
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

    public ProjectDTO getProjectById(String projectId) throws SQLException {
        ProjectDTO project = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_PROJECT_BY_ID;
                stm = conn.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String projectName = rs.getString("Name");
                    String projectMentor = rs.getString("MentorId");
                    String projectCoMentor = rs.getString("Co-Mentor");
                    int projectNumOfStudent = rs.getInt("NumOfStus");
                    boolean isSelected = rs.getBoolean("IsSelected");
                    String discription = rs.getString("Discription");
                    int semester = rs.getInt("semester");
                    SemesterDAO seDao = new SemesterDAO();
                    UserDAO usDao = new UserDAO();
                    project = new ProjectDTO(projectId, projectName, usDao.getUserById(projectMentor), projectCoMentor, projectNumOfStudent, isSelected, discription, seDao.getSemesterById(semester));
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

        return project;
    }

    public List<ProjectDTO> getListByMentor(String mentor) throws SQLException {
        List<ProjectDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_LIST_BY_MENTOR;
                stm = conn.prepareStatement(sql);
                stm.setString(1, mentor);
                rs = stm.executeQuery();
                while (rs.next()) {

                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getString("Name");
                    String projectMentor = rs.getString("MentorId");
                    String projectCoMentor = rs.getString("Co-Mentor");
                    int projectNumOfStudent = rs.getInt("NumOfStus");
                    boolean isSelected = rs.getBoolean("IsSelected");
                    String discription = rs.getString("Discription");
                    int semester = rs.getInt("semester");
                    SemesterDAO seDao = new SemesterDAO();
                    UserDAO usDao = new UserDAO();
                    list.add(new ProjectDTO(projectId, projectName, usDao.getUserById(projectMentor), projectCoMentor, projectNumOfStudent, isSelected, discription, seDao.getSemesterById(semester)));
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

    public boolean updateProjectIsSelected(String projectId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_PROJECT;
                stm = conn.prepareStatement(sql);
                stm.setBoolean(1, true);
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
    public boolean updateProjectDiscription(String projectId, String discription) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_PROJECT_DISCRIPTION;
                stm = conn.prepareStatement(sql);
                stm.setString(1, discription);
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

    public boolean insert(ArrayList<String> project) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = INSERT_PROJECT;
                stm = conn.prepareStatement(sql);

                stm.setString(1, project.get(1)); //ProjectId
                stm.setString(2, project.get(2)); //Name
                stm.setString(3, project.get(3)); //MentorId

                if ("NULL".equals(project.get(4).toUpperCase())) { //NULL
                    stm.setNull(4, Types.VARCHAR); //[Co-Mentor]
                } else {
                    stm.setString(4, project.get(4)); //[Co-Mentor]
                }

                stm.setInt(5, (int) Double.parseDouble(project.get(5))); //NumOfStus

                stm.setBoolean(6, false); //IsSelected
                stm.setNull(7, Types.NVARCHAR); //Discription
                stm.setNull(8, Types.INTEGER); //Semester
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
