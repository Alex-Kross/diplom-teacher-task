package com.bntu.diplom.teacherTask.services;



import com.bntu.diplom.teacherTask.models.Group;
import com.bntu.diplom.teacherTask.models.Student;
import com.bntu.diplom.teacherTask.models.Subject;
import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.repositories.GroupRepository;
import com.bntu.diplom.teacherTask.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public List<Student> parseListStudent(byte[] data, long groupId) throws IOException {
        Group group = groupRepository.findById(groupId).get();
        List<Student> students = new ArrayList<>();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            Workbook workbook = new XSSFWorkbook(byteArrayInputStream);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = datatypeSheet.iterator();
            if (rowIterator.hasNext()) {
                Row firstRow = rowIterator.next();
                Cell cell = firstRow.getCell(1);
                if (cell.getCellType() == CellType.STRING) {
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
                        if (currentRow.getCell(0) != null
                                && currentRow.getCell(0).getCellType() != CellType.BLANK) {
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
                                    parentEmail, parentPhone, group));
                        }
                    }
                }
            }
        } catch (IOException e) {
//            return new ArrayList<>();
        } catch (IllegalArgumentException e) {
//            return new ArrayList<>();
        }
        return students;
    }

    public void saveGroup(long groupId, byte[] bytes) throws IOException {
        List<Student> students = parseListStudent(bytes, groupId);
        groupRepository.findById(groupId).get().setStudents(students);
        log.info("Saving list student for group {}", groupRepository.findById(groupId).get().getName());
        studentRepository.saveAll(students);
    }


}
