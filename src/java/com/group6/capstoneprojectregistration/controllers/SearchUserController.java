/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.InvitationPendingDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.InvitationPendingDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "SearchUserController", urlPatterns = {"/SearchUserController"})
public class SearchUserController extends HttpServlet {

    private static final String ERROR = "search-not-exit-user.jsp";
    private static final String SUCCESS = "search-user-nogroup.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        String searchEmail = request.getParameter("txtEmail");

        UserDAO dao = new UserDAO();
        InvitationPendingDAO ipDao = new InvitationPendingDAO();

        HttpSession session = request.getSession();

        try {
                List<InvitationPendingDTO> listInvitationPending = ipDao.getCurrentInvitationPending();
                List<UserDTO> listUserNoGroup = dao.searchUserByEmail(searchEmail);
                List<UserDTO> userToRemove = new ArrayList<>();
                for (UserDTO user : listUserNoGroup) {
                    for (InvitationPendingDTO invi : listInvitationPending) {
                        if (user.getEmail().equals(invi.getUserInvited())) {
                            userToRemove.add(user);
                        }
                    }
                }
                listUserNoGroup.removeAll(userToRemove);
                if (listUserNoGroup.size() > 0) {
                    session.setAttribute("LIST_USER_NO_GROUP", listUserNoGroup);
                    url = SUCCESS;
                } else {
                    session.setAttribute("LIST_USER_NO_GROUP", null);
                    url = ERROR;
                }
        } catch (Exception e) {
            log("Error at SearchUserController " + e.toString());
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
