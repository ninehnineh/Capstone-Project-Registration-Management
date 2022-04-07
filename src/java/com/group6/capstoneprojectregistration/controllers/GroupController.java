/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDetailDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.ProjectDetailsDTO;
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
// Hiển thị list sinh viên theo group id
@WebServlet(name = "GroupController", urlPatterns = {"/GroupController"})
public class GroupController extends HttpServlet {

    private static final String ERROR = "group.jsp";
    private static final String SUCCESS = "group.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;

        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("USER");

        UserDAO usDao = new UserDAO();
        GroupDAO grDao = new GroupDAO();
        ProjectDetailDAO pdDao = new ProjectDetailDAO();
        GroupDTO currenGroup = user.getGroup();

        try {

            if (currenGroup != null) {
                GroupDTO group = grDao.getGroupByGroupId(currenGroup.getGroupId());
                session.setAttribute("GROUP", group);
                List<UserDTO> listUser = usDao.getListUserByGroupId(currenGroup.getGroupId());
                if (listUser.size() > 0) {
                    session.setAttribute("LIST_USER_IN_GROUP", listUser);
                    url = SUCCESS;
                }
                ProjectDetailsDTO projectDetail = pdDao.getProjectDetailByGroupId(currenGroup.getGroupId());
                if (projectDetail != null) {
                    session.setAttribute("DETAIL", projectDetail);
                }
            } else {
                request.setAttribute("MESSAGE", "Oops! You are not in any group, please contact your leader or create new group");
                session.setAttribute("DETAIL", null);
                session.setAttribute("LIST_USER_IN_GROUP", null);
                session.setAttribute("GROUP", null);
            }

        } catch (Exception e) {
            log("Error at GroupController " + e.toString());
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
