/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
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
@WebServlet(name = "MemberLeaveGroupController", urlPatterns = {"/MemberLeaveGroupController"})
public class MemberLeaveGroupController extends HttpServlet {

    private static final String ERROR = "group.jsp";
    private static final String SUCCESS = "group.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        String userId = request.getParameter("currentUser");
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        HttpSession session = request.getSession();
        UserDAO usDao = new UserDAO();
        GroupDAO gDao = new GroupDAO();

        try {
            boolean checkLeaveGroup = usDao.updateGroupByUserId(userId);
            UserDTO userAfterUpdate = usDao.getUserById(userId);
            GroupDTO currentGroupIdOfUser = userAfterUpdate.getGroup();
            GroupDTO group = gDao.getGroupByGroupId(groupId);
            if (group.getProject()==null) {

                if (checkLeaveGroup) {
                    if (currentGroupIdOfUser != null) {
                        List<UserDTO> listUserInGroup = usDao.getListUserByGroupId(currentGroupIdOfUser.getGroupId());
                        if (listUserInGroup.size() > 0) {
                            session.setAttribute("LIST_USER_IN_GROUP", listUserInGroup);
                        } else {
                            session.setAttribute("LIST_USER_IN_GROUP", null);
                            request.setAttribute("MESSAGE", "Oops! You are not in any group, please contact your leader or create new group");
                        }
                    } else {
                        session.setAttribute("LIST_USER_IN_GROUP", null);
                        request.setAttribute("MESSAGE", "Oops! You are not in any group, please contact your leader or create new group");
                    }
                    session.setAttribute("USER", userAfterUpdate);
                    url = SUCCESS;
                }
            }else{
                request.setAttribute("MESSAGE_LEAVE", "Oops! You are already in group that had sign a project, please contact your mentor if you want to leave!");
            }
        } catch (Exception e) {
            log("Error at MemberLeaveGroupController " + e.toString());
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
