/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.utils.ImportDocumentUtils;
import com.group6.capstoneprojectregistration.utils.ImportExcelUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author haitu
 */
@WebServlet(name = "ImportProjectController", urlPatterns = {"/ImportProjectController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ImportProjectController extends HttpServlet {

    private static final String SUCCESS = "AdminProjectController"; //load page
    //Dont have ERROR -> return message only

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = SUCCESS;
        HttpSession session = request.getSession();
        HashMap<String, String> listDocumentProject = new HashMap<>();
        try {

            for (Part part : request.getParts()) {
                InputStream inputStream = null;

                String fileName = extractFileName(part);
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();
                try {
                    inputStream = part.getInputStream();
                    String dataDir = this.getFolderUpload().getAbsolutePath() + File.separator;

                    String ext = FilenameUtils.getExtension(fileName);
                    String fileNameWithOutExt = FilenameUtils.removeExtension(fileName);

                    switch (ext) {
                        case "xls":
                            ImportExcelUtils.ImportProject(inputStream);
                            break;
                        case "docx":
                            part.write(dataDir + fileName);
                            listDocumentProject.put(fileNameWithOutExt, dataDir + fileName);
                            log(dataDir + fileName);
                            break;
                    }
                } catch (Exception e) {
                    log("Error at ImportProjectController -> Part" + e.toString());
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
            ImportDocumentUtils.ImportProjectDocument(listDocumentProject);
        } catch (Exception e) {
            log("Error at ImportProjectController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    //create file in server storage
    // C:\Users\haitu\Uploads
    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
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
