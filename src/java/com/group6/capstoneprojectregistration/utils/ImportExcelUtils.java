/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.utils;

import com.group6.capstoneprojectregistration.daos.GroupDAO;
import com.group6.capstoneprojectregistration.daos.ProjectDAO;
import com.group6.capstoneprojectregistration.daos.UserDAO;
import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author haitu
 */
public class ImportExcelUtils {

    public static void ImportProject(InputStream inputStream) throws IOException, SQLException {
        UserDAO userDao = new UserDAO();
        ProjectDAO projectDao = new ProjectDAO();
        GroupDAO groupDao = new GroupDAO();
        
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);

        //Insert Mentor sheet0
        //1/3 Create Students sheet0
        ArrayList<ArrayList<String>> listMentor = ImportExcelUtils.ReadDataFromXLS(wb, 0);

        for (ArrayList<String> mentor : listMentor) {
            boolean checkInsertMentor = userDao.insertMentor(mentor);
        }
        
        //Insert Project sheet1
        ArrayList<ArrayList<String>> list = ImportExcelUtils.ReadDataFromXLS(wb, 1);
        for (ArrayList<String> project : list) {
            boolean checkInsertProject = projectDao.insert(project);
            if (checkInsertProject == true) {

            }
        }

        //Update GroupProject sheet2
        HashMap<String, String> hashGroupProject = ImportExcelUtils.ReadGroupProjectFromExcel(wb);
        for (HashMap.Entry<String, String> set : hashGroupProject.entrySet()) {
            String projectId = set.getKey();
            String groupName = set.getValue();

            GroupDTO group = groupDao.getGroupByName(groupName);
            // if(groupId == true){
            //                
            // }
            //Update group, project
            //Insert projectDetail
            int groupId = group.getGroupId();
            boolean checkUpdateGroupProject = groupDao.updateGroup(projectId, groupId);
            boolean checkProjectIsSelected = projectDao.updateProjectIsSelected(projectId);
            //  if (checkUpdateGroupProject == true) {
            //
            //     }
            //  if (checkProjectIsSelected == true) {
            //
            //     }
        }
    }

    public static void ImportStudent(InputStream inputStream) throws IOException, SQLException {
        UserDAO userDao = new UserDAO();
        GroupDAO groupDao = new GroupDAO();

        HSSFWorkbook wb = new HSSFWorkbook(inputStream);

        //STUDENT SHEET 0
        //1/3 Create Students sheet0
        ArrayList<ArrayList<String>> list = ImportExcelUtils.ReadDataFromXLS(wb, 0);

        for (ArrayList<String> student : list) {
            boolean checkInsertStudent = userDao.insert(student);
        }

        //GROUPS SHEET 1
        HashMap<String, ArrayList<String>> hashGroup = ImportExcelUtils.ReadDataGroupFromExcel(wb);

        for (HashMap.Entry<String, ArrayList<String>> set : hashGroup.entrySet()) {
            //2/3 CreateGroup
            String groupName = set.getKey();
            boolean checkInsertGroup = groupDao.insertGroupName(groupName);
            //  if (checkGroupInsert == true) {
            //
            //     }

            GroupDTO group = groupDao.getGroupByName(groupName);
            //3/3 Update group's student
            ArrayList<String> students = set.getValue();
            int i = 0; //for count who is leader
            boolean isLeader = false;
            for (String studentId : students) {
                if (i == 0) {
                    isLeader = true;
                } else {
                    isLeader = false;
                }
                boolean checkUpdateGroup = userDao.updateGroupFromExcel(studentId, group, isLeader);
                i++;
                // if (checkGroupUpdate == true) {
                //
                // }
            }
        }
    }

    //sheet0 student(student)+project(mentor)
    //sheet1 project(project)
    private static ArrayList<ArrayList<String>> ReadDataFromXLS(HSSFWorkbook wb, int sheetId) throws FileNotFoundException, IOException {
        HSSFSheet sheet = wb.getSheetAt(sheetId);
        //evaluating cell type   
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

        ArrayList<ArrayList<String>> listCell = new ArrayList<>();

        for (Row row : sheet) //iteration over row using for each loop  
        {
            ArrayList<String> listRow = new ArrayList<>();
            for (Cell cell : row) //iteration over cell using for each loop  
            {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
                        //getting the value of the cell as a number  
                        listRow.add(Double.toString(cell.getNumericCellValue()).trim());
                        break;
                    case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
                        //getting the value of the cell as a string  
                        listRow.add(cell.getStringCellValue().trim());
                        break;
                }
            }
            listCell.add(listRow);
        }

        wb.close();
        listCell.remove(0); //remove header of excel
        return listCell;
    }

    //sheet1 student(group)
    private static HashMap<String, ArrayList<String>> ReadDataGroupFromExcel(HSSFWorkbook wb) throws FileNotFoundException, IOException {
        HSSFSheet sheet = wb.getSheetAt(1);
        //evaluating cell type   
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

        HashMap<String, ArrayList<String>> hashStudent = new HashMap<>();
        for (Row row : sheet) //iteration over row using for each loop  
        {
            if (row.getRowNum() != 0) {
                ArrayList<String> list = new ArrayList<>();
                String key = null;
                String value = null;
                for (Cell cell : row) //iteration over cell using for each loop  
                {
                    if (cell.getColumnIndex() != 0) {
                        switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
                                //getting the value of the cell as a number  
                                if (cell.getColumnIndex() == 1) {
                                    key = Double.toString(cell.getNumericCellValue()).trim();
                                } else {
                                    value = Double.toString(cell.getNumericCellValue()).trim();
                                }
                                break;
                            case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
                                //getting the value of the cell as a string  
                                if (cell.getColumnIndex() == 1) {
                                    key = cell.getStringCellValue().trim();
                                } else {
                                    value = cell.getStringCellValue().trim();
                                }
                                break;
                        }
                        if (value != null) {
                            list.add(value);
                        }
                    }
                }

                if (key != null && value != null) {
                    hashStudent.put(key, list);
                }

            }
        }

        wb.close();
        return hashStudent;
    }

    //sheet2 project(groupProject)
    private static HashMap<String, String> ReadGroupProjectFromExcel(HSSFWorkbook wb) throws FileNotFoundException, IOException {
        HSSFSheet sheet = wb.getSheetAt(2);
        //evaluating cell type   
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

        HashMap<String, String> hashGroupProject = new HashMap<>();
        for (Row row : sheet) //iteration over row using for each loop  
        {
            if (row.getRowNum() != 0) {
                String key = null;
                String value = null;
                for (Cell cell : row) //iteration over cell using for each loop  
                {
                    if (cell.getColumnIndex() != 0) {
                        switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
                                //getting the value of the cell as a number  
                                if (cell.getColumnIndex() == 1) {
                                    key = Double.toString(cell.getNumericCellValue()).trim();
                                } else {
                                    value = Double.toString(cell.getNumericCellValue()).trim();
                                }
                                break;
                            case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
                                //getting the value of the cell as a string  
                                if (cell.getColumnIndex() == 1) {
                                    key = cell.getStringCellValue().trim();
                                } else {
                                    value = cell.getStringCellValue().trim();
                                }
                                break;
                        }
                    }
                }

                if (key != null && value != null) {
                    hashGroupProject.put(key, value);
                }
            }
        }

        wb.close();
        return hashGroupProject;
    }
}