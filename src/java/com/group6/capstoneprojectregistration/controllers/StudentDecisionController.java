/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.EventDAO;
import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.InvitationPendingDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.EventDTO;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.InvitationPendingDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import java.io.IOException;
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
@WebServlet(name = "StudentDecisionController", urlPatterns = {"/StudentDecisionController"})
public class StudentDecisionController extends HttpServlet {

    private static final String ERROR = "group.jsp";
    private static final String SUCCESS = "GroupController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = ERROR;

        String studentDecision = request.getParameter("studentDecision");
        String invitedUserId = request.getParameter("invitedUserId");
        String sender = request.getParameter("sender");
        String emailReceiver = request.getParameter("emailReceiver");
        
        UserDAO usDao = new UserDAO();
        InvitationPendingDAO ipDao = new InvitationPendingDAO();
        EventDAO evDao = new EventDAO();
        
        try {

            UserDTO SendUser = usDao.getUserById(sender);
            UserDTO currentUser = usDao.getUserByEmail(emailReceiver);
            UserDTO userBack = usDao.getUserByEmail(emailReceiver);
            InvitationPendingDTO invite = ipDao.getUserPendingByEmail(emailReceiver, sender);
            HttpSession session = request.getSession();

            if (studentDecision.equals("Accept")) {
                boolean checkAddUserIntoGroup = usDao.addUserIntoGroup(SendUser, invitedUserId);
                if (checkAddUserIntoGroup) {
                    boolean checkDeleteMessage = evDao.deleteMessage(emailReceiver, SendUser);
                    boolean checkDeleteUserPending = ipDao.deleteUserPendingByUserInvitedAndLeaderId(emailReceiver, sender);
                    if (checkDeleteMessage && checkDeleteUserPending) {
                        boolean checkInsertAcceptEvnet = evDao.insertEvent(SendUser, currentUser, "Accept");
                        if (checkInsertAcceptEvnet) {
                            //user da co nhom, nen update lai USER tren session de dung
                            UserDTO userLogin = usDao.getUserByEmail(emailReceiver);
                            session.setAttribute("USER", userLogin);
                            List<EventDTO> listEvent = evDao.getAllEventByReceiverEmail(emailReceiver);
                            session.setAttribute("EVENT", listEvent);
                            request.setAttribute("ACCEPT", "Welcome to group " + SendUser.getGroup().getName());
                            url = SUCCESS;
                        }
                    }
                }
            } else {
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error at StudentDecisionController" + e.toString());
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
