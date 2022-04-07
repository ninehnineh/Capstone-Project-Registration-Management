/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDetailDAO;
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
@WebServlet(name = "StudentProjectPendingController", urlPatterns = {"/StudentProjectPendingController"})
public class StudentProjectPendingController extends HttpServlet {

    private static final String ERROR = "ProjectHadSign.jsp";
    private static final String SUCCESS = "ProjectHadSign.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("USER");

        ProjectDetailDAO dao = new ProjectDetailDAO();
        GroupDAO grDao = new GroupDAO();

        try {
            if (user.getGroup() != null) {
                GroupDTO group = grDao.getGroupThatHasApprovedProject(user.getGroup().getName(), true);
                List<ProjectDetailsDTO> listProject = dao.getProjectPendingByUserId(user.getUserId());
                if (listProject.size() > 0) {
                    session.setAttribute("LIST_PROJECT_PENDING", listProject);
                } else {
                    session.setAttribute("LIST_PROJECT_PENDING", null);
                    request.setAttribute("MESSAGE", "There is no project registered yet");
                }
                if (group != null) {
                    session.setAttribute("PROJECT_APPROVED", group);
                } else {
                    session.setAttribute("PROJECT_APPROVED", null);
                }
                url = SUCCESS;
            } else {
                System.out.println("hell");
                request.setAttribute("NOT_IN_GROUP", "There is no project registered yet");
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at StudentProjectPendingController " + e.toString());
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
