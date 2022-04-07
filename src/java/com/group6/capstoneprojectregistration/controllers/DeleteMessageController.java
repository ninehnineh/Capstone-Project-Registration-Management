/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.EventDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.EventDTO;
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
@WebServlet(name = "DeleteMessageController", urlPatterns = {"/DeleteMessageController"})
public class DeleteMessageController extends HttpServlet {

    public static final String ERROR = "group.jsp";
    public static final String SUCCESS = "group.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        String sender = request.getParameter("sender");
        String receiver = request.getParameter("receiver");
        String event = request.getParameter("event");
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("USER");

        EventDAO evdao = new EventDAO();
        EventDAO evDao = new EventDAO();

        try {
            switch (action) {
                case "Delete":
                    boolean checkDeleteEvent = evDao.deleteMessageByReceiverAndSender(receiver, sender, event);
                    if (checkDeleteEvent) {
                        List<EventDTO> listEvent = evdao.getAllEventByReceiverEmail(user.getEmail());
                        if (!listEvent.isEmpty()) {
                            session.setAttribute("EVENT", listEvent);
                        } else {
                            session.setAttribute("EVENT", null);
                        }
                        request.setAttribute("MESSAGE", "Delete message successful");
                        url = SUCCESS;
                    }
                    break;
                case "DeleteAll":
                    boolean checkDeleteAllEvent = evDao.deleteAllMessageByReceiverEmail(user.getEmail());
                    if (checkDeleteAllEvent) {
                        List<EventDTO> listEvent = evdao.getAllEventByReceiverEmail(user.getEmail());
                        if (!listEvent.isEmpty()) {
                            session.setAttribute("EVENT", listEvent);
                        } else {
                            session.setAttribute("EVENT", null);
                        }
                        url = SUCCESS;
                    }
                    break;
            }

        } catch (Exception e) {
            log("Error at ClearMessageController " + e.toString());
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
