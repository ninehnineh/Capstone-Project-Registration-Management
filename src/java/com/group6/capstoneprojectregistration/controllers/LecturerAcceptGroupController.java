/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.EventDAO;
import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.InvitationPendingDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDetailDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
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

/**
 *
 * @author PC
 */
@WebServlet(name = "LecturerAcceptGroupController", urlPatterns = {"/LecturerAcceptGroupController"})
public class LecturerAcceptGroupController extends HttpServlet {

    private static final String ERROR = "projectlist.jsp";
    private static final String SUCCESS = "LecturerProjectPendingController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String projectId = request.getParameter("projectId").trim();
        String currentUser = request.getParameter("sender");

        GroupDAO grDao = new GroupDAO();
        ProjectDAO prDao = new ProjectDAO();
        ProjectDetailDAO pdDao = new ProjectDetailDAO();
        EventDAO evDao = new EventDAO();
        UserDAO usDao = new UserDAO();
        InvitationPendingDAO ipDao = new InvitationPendingDAO();
        
        try {
            List<ProjectDetailsDTO> listProjectRegistedDuplicate = pdDao.getAllProjectDetails(projectId);
            List<UserDTO> listUserByGroupId = usDao.getListUserByGroupId(groupId);

            boolean checkDeleteProjectPending = pdDao.deleteAllProjectPendingByGroupId(groupId); // 1 project đã được chấp nhận và delete hết project còn lại mà nhóm đã dk trc đó 
            boolean checkUpdateGroup = grDao.updateGroup(projectId, groupId); // update status group đã được accept project
            boolean checkUpdateProject = prDao.updateProjectIsSelected(projectId); // update isSelected in tbl project 

            if (!listProjectRegistedDuplicate.isEmpty()) {
                boolean checkDeleteProjectPendingOfAnotherGroup = pdDao.deleteProjectRegistedOfAnotherGroup(projectId);// delete các nhóm khác có đăng ký trùng project
            }
            if (checkUpdateProject && checkUpdateGroup && checkDeleteProjectPending) {
                for (UserDTO user : listUserByGroupId) {
                    boolean checkSendingMessage = evDao.insertMessageOfLecturer(currentUser, user.getEmail(), "AcceptProject");
                    if (!checkSendingMessage) {
                        request.setAttribute("DENY", "Cant send message deny");
                    }
                }
                ipDao.deleteAllUserPending(groupId);
                request.setAttribute("ACCEPTED", "Accept Successful!");
                url = SUCCESS;
            } else {
                request.setAttribute("ACCEPTED", "Accept Fail!");
                url = ERROR;
            }

        } catch (Exception e) {
            log("Error at AcceptGroupController " + e.toString());
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
