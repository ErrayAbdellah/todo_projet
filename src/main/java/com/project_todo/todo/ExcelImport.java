package com.project_todo.todo;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.repositories.IUserRepo;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelImport {
    @Autowired
    private IUserRepo repo;
    public List<User> ReadDataFromExcel(String excelPath) throws EncryptedDocumentException, InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

        Workbook workbook = WorkbookFactory.create(new File(excelPath));
        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
        System.out.println("Retrieving Sheets using for-each loop");
        for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());

            DataFormatter dataFormatter = new DataFormatter();
            for (Row row: sheet) {



                String name = row.getCell(0).getStringCellValue();


                User user = new User();

                user.setName(name);

                repo.save(user);

                System.out.println(row.getCell(0).getDateCellValue());

            }

        }



        return null;
    }
}
