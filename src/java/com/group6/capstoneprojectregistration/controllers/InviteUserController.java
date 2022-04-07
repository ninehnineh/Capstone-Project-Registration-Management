/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.EventDAO;
import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.InvitationPendingDAO;
import com.group6.capstoneprojectregistration.daos.MessageEventDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDetailDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.EventDTO;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.ProjectDetailsDTO;
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
// Khi leader click invite 1 sinh vien tren danh sach
// 
@WebServlet(name = "InviteUserController", urlPatterns = {"/InviteUserController"})
public class InviteUserController extends HttpServlet {

    private static final String ERROR = "group.jsp";
    private static final String SUCCESS = "student-with-no-group.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;

        String receiverEmail = request.getParameter("receiver");
        String senderEmail = request.getParameter("sender");
        String groupName = request.getParameter("groupName");

        UserDAO usDao = new UserDAO();
        GroupDAO grDao = new GroupDAO();
        EventDAO evDao = new EventDAO();
        ProjectDAO prDao = new ProjectDAO();
        ProjectDetailDAO pdDao = new ProjectDetailDAO();
        InvitationPendingDAO peDao = new InvitationPendingDAO();

        HttpSession session = request.getSession();

        try {
            UserDTO sender = usDao.getUserByEmail(senderEmail);
            GroupDTO group = grDao.getGroupByName(groupName);
            UserDTO user = (UserDTO) session.getAttribute("USER");
            int numOfStudent = usDao.countStudentInGroup(user.getGroup().getGroupId());
            UserDTO receiver = usDao.getUserByEmail(receiverEmail);
            EventDTO event = evDao.getEventByReceiverAndEvent(receiverEmail,sender.getUserId());
            ProjectDetailsDTO projectDetail = pdDao.getProjectDetailByGroupId(group.getGroupId());
            if (projectDetail == null) {
                if (event == null) {
                    if (numOfStudent < 5) {
                        boolean checkUserPending = peDao.insertPendingUser(sender, group, receiverEmail);
                        boolean checkInviteEvent = evDao.insertInviteEvent(receiverEmail, sender);
                        if (checkUserPending && checkInviteEvent) {
                            request.setAttribute("INVITE", "Invite " + receiver.getUserName() + " successfully!");
                            url = SUCCESS;
                        } else {
                            request.setAttribute("INVITE", "Invitation failed!");
                            url = ERROR;
                        }
                    } else {
                        request.setAttribute("INVITE", "Your team have enough member!");
                        url = ERROR;
                    }
                } else {
                    request.setAttribute("INVITE", "This user already invited");
                }
            } else {
                
                request.setAttribute("INVITE", "This member cannot be invited because your group has already registered the project ");
            }

        } catch (Exception e) {
            log("Error at InviteUserController" + e.toString());
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
