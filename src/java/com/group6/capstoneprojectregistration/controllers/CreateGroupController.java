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
// Sinh viên tạo một nhóm bằng cách nhập Group Name vào và click nut create tren studentgroup.jsp
// Khi click nút cteate tên group sẽ được thêm vào bảng Group
// Đồng thời update group id cho sinh viên 
// sau đó dispatcher sang groupcontroller
@WebServlet(name = "CreateGroupController", urlPatterns = {"/CreateGroupController"})
public class CreateGroupController extends HttpServlet {

    private static final String ERROR = "group.jsp";
    private static final String SUCCESS = "GroupController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = ERROR;
        GroupDAO grDao = new GroupDAO();
        GroupDTO group;

        UserDAO usDao = new UserDAO();
        UserDTO user;
        try {

            String groupName = request.getParameter("groupName");
            String email = request.getParameter("email");
            user = usDao.getUserByEmail(email);

            if (user.getGroup() != null) {
                request.setAttribute("BUG", "You already has group!");
                url = ERROR;
            } else {
                group = grDao.getGroupByName(groupName);
                if (group != null) {
                    request.setAttribute("DUPLICATE", "Group Name is existing! Please choose another name.");
                    url = ERROR;
                } else {
                    boolean insertGroupName = grDao.insertGroupName(groupName);
                    group = grDao.getGroupByName(groupName);
                    boolean insertGroupId = usDao.updateGroupUser(usDao.getUserByEmail(email), group);
                    HttpSession session = request.getSession();
                    if (insertGroupId && insertGroupName) {
                        user = usDao.getUserByEmail(email);
                        session.setAttribute("USER", user);
                        request.setAttribute("SUCCESS", "Group has been initialized");
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Group cannot be initialized!");
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CreateGroupController" + e.toString());
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
