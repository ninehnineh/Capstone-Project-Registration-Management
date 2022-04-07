/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.config.Checkmail;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.GoogleDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import com.group6.capstoneprojectregistration.utils.GoogleUtils;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    public static final String ERROR = "index.jsp";
    public static final String SUCCESS = "index.jsp";
    public static final String STUDENT = "ProjectController";
    public static final String LECTURER = "ListProjectGuidingController";
    public static final String ADMIN = "AdminManageStudentNoGroupController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        try {
            String code = request.getParameter("code");

            if (code == null || code.isEmpty()) {
                RequestDispatcher dis = request.getRequestDispatcher(url);
                dis.forward(request, response);
            } else {
                String acccessToken = GoogleUtils.getToken(code);

                GoogleDTO ggpojo = GoogleUtils.getUserInfo(acccessToken);

//                Checkmail checkmail = new Checkmail();
//                if (!checkmail.validate(ggpojo.getEmail())) {
//                    request.setAttribute("ERROR_LOGIN", "Please login by FPT email!");
//                    url = ERROR;
//                } else {
                    UserDAO dao = new UserDAO();
                    UserDTO user = dao.getUserByEmail(ggpojo.getEmail());
                    HttpSession session = request.getSession();
                    if (user != null) {
                        switch (user.getRole().getName()) {
                            case "Student":
                                url = STUDENT;
                                break;
                            case "Mentor":
                                url = LECTURER;
                                break;
                            case "Admin":
                                url = ADMIN;
                                break;
                        }
                        session.setAttribute("USER", user);
                    } else {
                        request.setAttribute("ERROR", "Sorry! Your account is not supported!");
                    }
//                }
            }
        } catch (Exception e) {
            log("error at LogingoogleController:" + e.toString());
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
