/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.InvitationPendingDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDetailDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.InvitationPendingDTO;
import com.group6.capstoneprojectregistration.dtos.ProjectDetailsDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
@WebServlet(name = "StudentNoGroupController", urlPatterns = {"/StudentNoGroupController"})
public class StudentNoGroupController extends HttpServlet {

    private static final String ERROR = "student-with-no-group.jsp";
    private static final String SUCCESS = "student-with-no-group.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        ProjectDetailDAO pdDao = new ProjectDetailDAO();
        InvitationPendingDAO ipDao = new InvitationPendingDAO();
        GroupDAO grDao = new GroupDAO();
        HttpSession session = request.getSession();
        UserDAO dao = new UserDAO();
        UserDTO user = (UserDTO) session.getAttribute("USER");
//        List<InvitationPendingDTO> invitation = (List<InvitationPendingDTO>) session.getAttribute("INVITATION");

        try {
            List<InvitationPendingDTO> invitation = ipDao.getInvitationByUser(user.getUserId());

            ProjectDetailsDTO projectDetail = pdDao.getProjectDetailByGroupId(user.getGroup().getGroupId());
            GroupDTO group = grDao.getGroupThatHasApprovedProject(user.getGroup().getName(), true);
            List<UserDTO> listUserNoGroup = dao.getListNoGroupUser(1);

            List<UserDTO> toRemove = new ArrayList<>();
            for (InvitationPendingDTO userInvited : invitation) {
                for (UserDTO userNoGroup : listUserNoGroup) {
                    if (userInvited.getUserInvited().equals(userNoGroup.getEmail())) {
                        toRemove.add(userNoGroup);
                    }
                }
            }
            listUserNoGroup.removeAll(toRemove);
            int count = listUserNoGroup.size();
            if (listUserNoGroup.size() > 0) {
                session.setAttribute("LIST_USER_NO_GROUP", listUserNoGroup);
                url = SUCCESS;
            } else {
                session.setAttribute("LIST_USER_NO_GROUP", null);
                url = SUCCESS;
            }

            session.setAttribute("CURRENT_PROJECT", projectDetail);
            session.setAttribute("CURRENT_GROUP", group);
        } catch (Exception e) {
            log("Error at StudentNoGroupController " + e.toString());
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
