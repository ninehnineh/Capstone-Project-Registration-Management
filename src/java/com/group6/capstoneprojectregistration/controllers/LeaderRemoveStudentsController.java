/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.EventDAO;
import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDetailDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.ProjectDTO;
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
@WebServlet(name = "LeaderRemoveStudentsController", urlPatterns = {"/LeaderRemoveStudentsController"})
public class LeaderRemoveStudentsController extends HttpServlet {

    private static final String ERROR = "group.jsp";
    private static final String SUCCESS = "group.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        HttpSession session = request.getSession();
        String userId = request.getParameter("receiverId");
        String receiverEmail = request.getParameter("receiverEmail");
        String sender = request.getParameter("sender");
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String groupName = request.getParameter("groupName");

        ProjectDetailDAO pdDao = new ProjectDetailDAO();
        EventDAO evDao = new EventDAO();
        UserDAO usDao = new UserDAO();
        GroupDAO grDao = new GroupDAO();

        try {
            GroupDTO group = grDao.getGroupThatHasApprovedProject(groupName, true);
            UserDTO currentUser = usDao.getUserById(sender);
            UserDTO receiveUser = usDao.getUserByEmail(receiverEmail);
            ProjectDetailsDTO projectDetail = pdDao.getProjectDetailByGroupId(groupId);
            if (projectDetail == null && group == null) {
                boolean checkRemoveStudent = usDao.removeStudentFromGroupByUserId(userId);
                if (checkRemoveStudent) {
                    evDao.insertEvent(receiveUser, currentUser, "LeaderRemove");
                    List<UserDTO> listUserInGroup = usDao.getListUserByGroupId(currentUser.getGroup().getGroupId());
                    session.setAttribute("LIST_USER_IN_GROUP", listUserInGroup);
                    request.setAttribute("REMOVE", "Remove Successfully");
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("USER_HAVE_GROUP", "Can't remove this member, cause your group has already registered the project ");
            }
        } catch (Exception e) {
            log("Error at LeaderRemoveStudentsController " + e.toString());
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
