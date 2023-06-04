package com.bntu.diplom.teacherTask.services;



import com.bntu.diplom.teacherTask.models.Group;
import com.bntu.diplom.teacherTask.models.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class StudentService {

    public List<Student> parseListStudent(String pathToFile) {
        List<Student> students = new ArrayList<>();
        try (FileInputStream excelFile = new FileInputStream(pathToFile)) {
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = datatypeSheet.iterator();
            if (rowIterator.hasNext()) {
                Row firstRow = rowIterator.next();
                Cell cell = firstRow.getCell(1);
                if (cell.getCellType() == CellType.STRING) {
//                    String[] headerWords = cell.getStringCellValue().split("\s|,\s|,");
                    long id = 1;
                    while (rowIterator.hasNext()) {
                        String surname = "";
                        String name = "";
                        String patronymic = "";
                        long ordinalNumber = 0;
                        String email = "";
                        String phone = "";
                        String parentFullName = "";
                        String parentEmail = "";
                        String parentPhone = "";
                        Row currentRow = rowIterator.next();

                        ordinalNumber = (long) currentRow.getCell(0).getNumericCellValue();

                        Cell studentFullNameCell = currentRow.getCell(1);
                        String[] words = studentFullNameCell.getStringCellValue().split(" ");
                        surname = words[0];
                        name = words[1];
                        patronymic = words[2];

                        email = currentRow.getCell(2).getStringCellValue();
                        phone = currentRow.getCell(3).getStringCellValue();
                        parentFullName = currentRow.getCell(4).getStringCellValue();
                        parentEmail = currentRow.getCell(5).getStringCellValue();
                        parentPhone = currentRow.getCell(6).getStringCellValue();

                        students.add(new Student(
                                ordinalNumber,
                                name, surname,
                                patronymic, email,
                                phone, parentFullName,
                                parentEmail, parentPhone, new Group()));
                        id++;
                    }
                }
            }
        } catch (NullPointerException e ) {

        } catch (IOException e ) {

        }
        return students;
    }
}
