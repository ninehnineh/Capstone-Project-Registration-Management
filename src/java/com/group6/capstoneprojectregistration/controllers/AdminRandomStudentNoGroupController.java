/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.ProjectDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.ProjectDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections4.ListUtils;

/**
 *
 * @author PC
 */
@WebServlet(name = "AdminRandomStudentNoGroupController", urlPatterns = {"/AdminRandomStudentNoGroupController"})
public class AdminRandomStudentNoGroupController extends HttpServlet {

    private static final String ERROR = "adminStudents.jsp";
    private static final String SUCCESS = "adminStudents.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserDAO usDao = new UserDAO();
        ProjectDAO prDao = new ProjectDAO();
        Random rand = new Random();
        HttpSession session = request.getSession();

        try {
            List<ProjectDTO> listNotSelectedProject = prDao.getAllNotSelectedProject(false);
            List<UserDTO> listUserNoGroup = usDao.getListNoGroupUser(1);
            List<List<UserDTO>> splitGroups = ListUtils.partition(listUserNoGroup, 4);
            List<ProjectDTO> listProjectRandom = prDao.getRandomProject(listNotSelectedProject, splitGroups.size());
            List<List<UserDTO>> listtt = new ArrayList<>();
            listtt.addAll(splitGroups);
            Iterator<List<UserDTO>> it = splitGroups.iterator();
            List<UserDTO> listNoGroupAfterRandom = new ArrayList<>();
            List<List<UserDTO>> listne = new ArrayList<>();

//            while (it.hasNext()) {
//                List<UserDTO> userr = it.next();
//                System.out.println(userr);
//                listne.add(userr);
////                System.out.println(userr); // 7 list
////                Iterator<UserDTO> itt = userr.iterator();
////                UserDTO us = itt.next();
////                System.out.println(itt.next());
////                System.out.println(us);
//                int indexOf7ElementInList = 2;
//                int indexOf2Element = 0;
//                while (userr.size() < 4) { // list 2 element no group
//                    List<UserDTO> mekiep = listne.get(listne.size() - indexOf7ElementInList);
//                    UserDTO data = userr.get(indexOf2Element);
//                    mekiep.add(data);
//                    if (mekiep.size() == 5) {
//                        indexOf7ElementInList++;
//                        indexOf2Element++;
//                    }
//
//                    System.out.println(mekiep);
////                    System.out.println(mekiep.toString());
////                    System.out.println(userr.get(1));
////                    int index = 0;
////                    for (UserDTO user : userr) {
////                        System.out.println(userr);
//////                      listne.get(index).add(user);
////                        List<UserDTO> userrr = listne.get(index);
////                        userrr.add(user);
////
////                        listne.set(index, userrr);
//////                        System.out.println(userr); // 1 list size < 4
////                        System.out.println(user); // 1 element
//////                        userr.remove(user);
////                        index++;
////                    }
//                }
////                else {
////                    listne.add(userr);
////                }
//            }
//            for (List<UserDTO> listne1 : listne) {
//                System.out.println(listne1.toString());
//            }
            if (listtt.size() > 0 && listNotSelectedProject.size() > 0) {
                for (List<UserDTO> group : listtt) {
                    if (group.size() < 4) {
                        for (UserDTO groupdto : group) {
                            
                        }
                    } else {
                        listne.add(group);
                    }
                }
                session.setAttribute("SPLIT_PROJECT", listProjectRandom);
                session.setAttribute("SPLIT_GROUP", listne);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at StudentNoGroupRandomController" + e.toString());
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
