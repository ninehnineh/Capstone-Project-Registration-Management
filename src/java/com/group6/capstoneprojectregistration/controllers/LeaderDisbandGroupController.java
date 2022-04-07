/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDetailDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.ProjectDetailsDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "LeaderDisbandGroupController", urlPatterns = {"/LeaderDisbandGroupController"})
public class LeaderDisbandGroupController extends HttpServlet {

    private static final String ERROR = "group.jsp";
    private static final String SUCCESS = "group.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String currentUserId = request.getParameter("currentUserId");
        String groupName = request.getParameter("groupName");

        HttpSession session = request.getSession();
        UserDAO usDao = new UserDAO();
        GroupDAO grDao = new GroupDAO();
        ProjectDetailDAO pdDao = new ProjectDetailDAO();

        try {
            GroupDTO groupDto = grDao.getGroupThatHasApprovedProject(groupName, true);
            List<UserDTO> listUserInGroup = usDao.getListUserByGroupId(groupId);
            GroupDTO group = grDao.getGroupByGroupId(groupId);
            ProjectDetailsDTO projectDetail = pdDao.getProjectDetailByGroupId(groupId);
            if (projectDetail == null && groupDto == null) {
                if (group != null) {
                    for (UserDTO user : listUserInGroup) {
                        usDao.updateGroupAndIsLeaderByUserId(user.getUserId());
                    }
                    grDao.deleteGroupById(groupId);
                    UserDTO user = usDao.getUserById(currentUserId);
                    session.setAttribute("USER", user);
                    session.setAttribute("LIST_USER_IN_GROUP", null);
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("MESSAGE_DISBAND", "Unable to disband, your team has registered the project ");
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("ERROR at LeaderDisbandGroupController " + e.toString());
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
