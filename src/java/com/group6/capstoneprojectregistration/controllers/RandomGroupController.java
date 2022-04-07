/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.ProjectDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "RandomGroupController", urlPatterns = {"/RandomGroupController"})
public class RandomGroupController extends HttpServlet {

    private static final String ERROR = "AdminManageStudentNoGroupController";
    private static final String SUCCESS = "AdminManageStudentNoGroupController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        int count = 0;
        int group = 0;
        int counting = 0;
        String templateName = "GroupRandom ";

        UserDAO usDao = new UserDAO();
        GroupDAO grDao = new GroupDAO();
        ProjectDAO prDao = new ProjectDAO();

        HttpSession session = request.getSession();
        String groupname = request.getParameter("groupName");

        List<List<UserDTO>> splitStudent = (List<List<UserDTO>>) session.getAttribute("SPLIT_GROUP");
        List<ProjectDTO> listProject = (List<ProjectDTO>) session.getAttribute("SPLIT_PROJECT");

        try {
            for (List<UserDTO> listSplitStudent : splitStudent) {
                if (listSplitStudent.size() == 4) { // chia các thành viên thành các nhóm 4 người
                    String temp = templateName + counting; // random tên nhóm
                    boolean checkInsertNewGroup = grDao.insertNewGroup(temp); // new group với tên nhóm random
//                    ProjectDTO project = listProject.get(listProject.size() - 1);
                    List<GroupDTO> listGroup = grDao.getAllGroup(); // get all group hiện có + group mới thêm ở bước trên
                    GroupDTO cuGroup = listGroup.get(listGroup.size() - 1); // lấy group mới add vào table group
                    Random rand = new Random();
                    int numberOfElements = 1;
                    for (int i = 0; i < numberOfElements; i++) {
                        int randomIndex = rand.nextInt(listProject.size());
                        ProjectDTO project = listProject.get(randomIndex);
                        session.setAttribute("SPLIT_PROJECT", project);
                        boolean checkUpdateGroup = grDao.updateGroup(project.getProjectId(), cuGroup.getGroupId());
                        boolean checkUpdateProject = prDao.updateProjectIsSelected(project.getProjectId());
                        listProject.remove(randomIndex);
                    }
                    counting++;
                    for (UserDTO student : listSplitStudent) { // duyệt student chưa có nhóm
                        // update attribute group của tbl user thành group id của group mới add
                        boolean checkUpdateGroupUser = usDao.updateGroupByUserId(student.getUserId(), cuGroup.getGroupId());
                        UserDTO user = usDao.getUserById(student.getUserId());
                        session.setAttribute("USER", user);
                        count++;// tăng số lượng sinh viên đã add vào group
                        if (count == 4) { // số lượng student đủ 4 thì reset biến count
                            group++;
                            count = 0;
                        }
                    }
                    url = SUCCESS;
                } else {
                    List<List<UserDTO>> listUserStillNoGroup = new ArrayList<>();
                    listUserStillNoGroup.add(listSplitStudent);
//                    for (List<UserDTO> listUserNoGroup : listUserStillNoGroup) {
//                        for (List<UserDTO> groupOfStudents : splitStudent) {
//                            if (splitStudent.size() == 4) {
//                                groupOfStudents.add(listUserNoGroup.get(listUserNoGroup.size() - 1));
//                                
//                            }
//                        }
//                    }
//                    session.setAttribute("ADMIN_LIST_USER_NO_GROUP", listUserStillNoGroup);
//                    System.out.println("le 1 em");
                    url = SUCCESS;
                }

            }
            session.setAttribute("SPLIT_GROUP", null);
//            splitStudent.clear();
//            System.out.println(splitStudent);
        } catch (Exception e) {
            log("Error at RandomGroupController " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
