package com.project_todo.todo.controllers;

import com.project_todo.todo.excel.UserExcelExporter;
import com.project_todo.todo.excel.UserExcelImporter;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.services.IUserServices;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.CellType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final IUserServices services;

    @GetMapping
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> listUsers = services.listAll();
        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

        excelExporter.export(response);
    }

//    @GetMapping
//    public void importExcel(HttpServletResponse request) throws IOException {
//        File file = new File("path/to/excel/file.xlsx");
//        InputStream inputStream = new FileInputStream(file);
//
//        UserExcelImporter userExcelImporter = new UserExcelImporter();
//        userExcelImporter.importUsers(inputStream);
//
//    }
//    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
//    public ResponseEntity<List<User>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
//        HttpStatus status = HttpStatus.OK;
//        List<User> userList = new ArrayList<>();
//
//        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
//        XSSFSheet worksheet = workbook.getSheetAt(0);
//
//        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
//            if (index > 0) {
//                User user = new User();
//
//                XSSFRow row = worksheet.getRow(index);
//                Integer id = (int) row.getCell(0).getNumericCellValue();
//
//                user.setId(id);
//                user.setName(row.getCell(1).getStringCellValue());
//                user.setLastName(row.getCell(2).getStringCellValue());
//                user.setEmail(row.getCell(3).getStringCellValue());
//
//                userList.add(user);
//            }
//        }
//
//        return new ResponseEntity<>(userList, status);
//    }
//@RequestMapping(value = "/import-excel", method = RequestMethod.POST)
//public ResponseEntity<List<User>> importExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
//    HttpStatus status = HttpStatus.OK;
//    List<User> userList = new ArrayList<>();
//
//    try (InputStream inputStream = file.getInputStream();
//         XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
//
//        XSSFSheet worksheet = workbook.getSheetAt(0);
//
//        for (int index = 1; index <= worksheet.getLastRowNum(); index++) {
//            XSSFRow row = worksheet.getRow(index);
//
//            String id = row.getCell(0).getStringCellValue();
//            String name = row.getCell(1).getStringCellValue();
//            String lastName = row.getCell(2).getStringCellValue();
//            String email = row.getCell(3).getStringCellValue();
//
//            User user = new User();
//            user.setId(Integer.parseInt(id));
//            user.setName(name);
//            user.setLastName(lastName);
//            user.setEmail(email);
//
//            userList.add(user);
//        }
//    } catch (IOException e) {
//        status = HttpStatus.INTERNAL_SERVER_ERROR;
//    }
//
//    return new ResponseEntity<>(userList, status);
//}
    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<User>> importExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<User> userList = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int index = 1; index <= worksheet.getLastRowNum(); index++) {
                XSSFRow row = worksheet.getRow(index);

                String id;
                if (row.getCell(0).getCellTypeEnum() == CellType.NUMERIC) {
                    id = String.valueOf((int) row.getCell(0).getNumericCellValue());
                } else {
                    id = row.getCell(0).getStringCellValue();
                }

                String name = row.getCell(1).getStringCellValue();
                String lastName = row.getCell(2).getStringCellValue();
                String email = row.getCell(3).getStringCellValue();

                User user = new User();
                user.setId(Integer.parseInt(id));
                user.setName(name);
                user.setLastName(lastName);
                user.setEmail(email);

                userList.add(user);
            }
        } catch (IOException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(userList, status);
    }

}
