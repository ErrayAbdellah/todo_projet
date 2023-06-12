package com.project_todo.todo.excel;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.project_todo.todo.model.entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class UserExcelImporter {
    public List<User> importUsers(InputStream inputStream) throws IOException {
        List<User> users = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        // Skip the header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            User user = readUserFromRow(row);
            if (user != null) {
                users.add(user);
            }
        }

        workbook.close();
        return users;
    }

    private User readUserFromRow(Row row) {
        User user = new User();

        Cell cell;

        // Read User ID
        cell = row.getCell(0);
        if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            user.setId((int) cell.getNumericCellValue());
        }

        // Read First Name
        cell = row.getCell(1);
        if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
            user.setName(cell.getStringCellValue());
        }

        // Read Last Name
        cell = row.getCell(2);
        if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
            user.setLastName(cell.getStringCellValue());
        }

        // Read Email
        cell = row.getCell(3);
        if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
            user.setEmail(cell.getStringCellValue());
        }

        return user;
    }
}
