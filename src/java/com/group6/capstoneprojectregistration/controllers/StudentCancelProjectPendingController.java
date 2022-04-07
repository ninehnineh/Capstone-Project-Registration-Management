/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.ProjectDetailDAO;
import com.group6.capstoneprojectregistration.dtos.ProjectDetailsDTO;
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
@WebServlet(name = "StudentCancelProjectPendingController", urlPatterns = {"/StudentCancelProjectPendingController"})
public class StudentCancelProjectPendingController extends HttpServlet {

    private static final String ERROR = "StudentProjectPendingController";
    private static final String SUCCESS = "StudentProjectPendingController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        String projectId = request.getParameter("projectId");
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        ProjectDetailDAO pbDao = new ProjectDetailDAO();

        HttpSession session = request.getSession();

        try {
            boolean checkDeleteProjectDetail = pbDao.deleteProjectDetailByProjectIdAndGroupId(groupId, projectId);
            List<ProjectDetailsDTO> listProjectDetail;
            listProjectDetail = pbDao.getAllProjectDetailByGroupId(groupId);
            if (checkDeleteProjectDetail) {
                if (listProjectDetail.size() > 0) {
                    session.setAttribute("LIST_PROJECT_PENDING", listProjectDetail);
                    url = SUCCESS;
                } else {
                    session.setAttribute("LIST_PROJECT_PENDING", null);
                    url = ERROR;
                }
            }
        } catch (Exception e) {
            log("Error at StudentCancelProjectPendingController " + e.toString());
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
