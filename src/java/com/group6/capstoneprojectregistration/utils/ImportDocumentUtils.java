/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.utils;

import com.group6.capstoneprojectregistration.daos.ProjectDAO;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

/**
 *
 * @author haitu
 */
public class ImportDocumentUtils {
    public static void ImportProjectDocument(HashMap<String, String> listDocumentProject) throws Exception {
        for (HashMap.Entry<String, String> set : listDocumentProject.entrySet()) {
            String fileNameWithOutExt = set.getKey();
            String fileUrl = set.getValue();
            ConvertDocumentToHTML(fileUrl, fileNameWithOutExt);
        }
    }
    
    private static boolean ConvertDocumentToHTML(String fileUrl, String fileNameWithOutExt) throws Exception {
        //Read and Convert
        DocumentConverter converter = new DocumentConverter();
        Result<String> result = converter.convertToHtml(new File(fileUrl));
        String discription = result.getValue(); // The generated HTML
     
        ProjectDAO dao = new ProjectDAO();
        boolean check = dao.updateProjectDiscription(fileNameWithOutExt, discription);
        return check;
    }
}