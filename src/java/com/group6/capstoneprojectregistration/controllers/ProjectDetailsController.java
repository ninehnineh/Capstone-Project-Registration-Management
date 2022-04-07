/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.ProjectDAO;
import com.group6.capstoneprojectregistration.dtos.ProjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ProjectDetailsController", urlPatterns = {"/ProjectDetailsController"})
public class ProjectDetailsController extends HttpServlet {

    private static final String ERROR = "pages/account/projectdetails.jsp";
    private static final String SUCCESS = "pages/account/projectdetails.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;

        String projectName = request.getParameter("projectName");
//        String projectMentor = request.getParameter("projectMentor");
//        String projectCoMentor = request.getParameter("projectCoMentor");
//        String projectNumOfStu = request.getParameter("projectNumOfStu");
//        String projectDiscription = request.getParameter("projectDiscription");
        
        try {
            ProjectDAO proDao = new ProjectDAO();
            ProjectDTO project = proDao.getProject(projectName);
            HttpSession session = request.getSession();
            
            if (project != null) {
                session.setAttribute("PROJECT_DETAILS", project);
                url = SUCCESS;
            }
            
        } catch (Exception e) {
            log("Error at ProjectDetailsController" + e.toString());
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
