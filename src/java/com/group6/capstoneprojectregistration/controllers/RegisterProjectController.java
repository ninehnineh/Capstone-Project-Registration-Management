/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.InvitationPendingDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDetailDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.ProjectDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import java.io.IOException;
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
@WebServlet(name = "RegisterProjectController", urlPatterns = {"/RegisterProjectController"})
public class RegisterProjectController extends HttpServlet {

    private static final String ERROR = "projects.jsp";
    private static final String SUCCESS = "projects.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        String projectId = request.getParameter("projectId").trim();
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        ProjectDetailDAO pdDao = new ProjectDetailDAO();
        UserDAO userDao = new UserDAO();
        ProjectDAO projectDao = new ProjectDAO();
        GroupDAO grDao = new GroupDAO();
        InvitationPendingDAO ipDao = new InvitationPendingDAO();

        try {
            boolean checkInsertProjectId = false;

            ProjectDTO project = projectDao.getProjectById(projectId);
            GroupDTO grDto = grDao.getGroupByGroupId(groupId);

            int count = userDao.countStudentInGroup(groupId);
            if (count == project.getNumOfStus()) { //số thành viên trong nhóm = projectnumber
                if (!grDto.isApproved()) {//kiểm tra nhóm đã đăng kí thành công đồ án chưa
                    if (pdDao.getProjectDetailByGroupIdAndProjectId(groupId, projectId) == null) {//ko đăng kí 1 đồ án 2 lần
                        checkInsertProjectId = pdDao.insertProjectId(projectId, groupId);
                        if (checkInsertProjectId) {//check insert thành công hay ko                   
                            request.setAttribute("INSERT_PROJECTID", "Register Successful");
                            url = SUCCESS;
                        } else {
                            request.setAttribute("INSERT_PROJECTID", "Register Fail");
                            url = ERROR;
                        }
                    } else {
                        request.setAttribute("INSERT_PROJECTID", "This project is already registed");
                        url = ERROR;
                    }
                } else {
                    request.setAttribute("INSERT_PROJECTID", "Your group already have a project");
                }
                ipDao.deleteAllUserPending(groupId);
            } else {
                request.setAttribute("INSERT_PROJECTID", "Your group does not have enough members or exceeds the allowed"
                        + " number to register for this project ");
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error at ProjectController" + e.toString());
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
