package com.bntu.diplom.teacherTask.services;



import com.bntu.diplom.teacherTask.models.*;
import com.bntu.diplom.teacherTask.repositories.GroupRepository;
import com.bntu.diplom.teacherTask.repositories.GroupStudentTeacherRepository;
import com.bntu.diplom.teacherTask.repositories.StudentRepository;
import com.bntu.diplom.teacherTask.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final GroupStudentTeacherRepository groupStudentTeacherRepository;

    public List<GroupStudentTeacher> parseListStudent(byte[] data, Group group, long teacherId) throws IOException {
        List<GroupStudentTeacher> groupStudentTeachers = group
                .getGroupStudentTeachers();
        Teacher teacher = teacherRepository.findById(teacherId).get();
//        Group group = groupRepository.findById(groupId).get();
        List<GroupStudentTeacher> unionList = new ArrayList<>();
//        List<Student> students = new ArrayList<>();
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
//                              TODO: переделать добавление студентов в группу
                            Student student = new Student(
                                    ordinalNumber, name, surname,
                                    patronymic, email, phone, parentFullName,
                                    parentEmail, parentPhone);
                            GroupStudentTeacher groupStudentTeacher = new GroupStudentTeacher(teacher, student, group);
//                            groupStudentTeacher.setStudent(student);
//                            groupStudentTeacher.setTeacher(teacher);
//                            groupStudentTeacher.setGroup(group);
                            unionList.add(groupStudentTeacher);
//////////////////////////////////////
//                            teacher.addUnion(groupStudentTeacher);
//                            group.addUnion(groupStudentTeacher);
//                            student.addUnion(groupStudentTeacher);
/////////////////////////////////////
//                            List<GroupStudentTeacher> groupStudentTeachers1 = teacher.getGroupStudentTeachers();
//                            groupStudentTeachers1.add(groupStudentTeacher);
//                            List<GroupStudentTeacher> groupStudentTeachers2 = group.getGroupStudentTeachers();
//                            groupStudentTeachers2.add(groupStudentTeacher);
//                            List<GroupStudentTeacher> groupStudentTeachers3 = student.getGroupStudentTeachers();
//                            groupStudentTeachers3.add(groupStudentTeacher);
//////////////////////////////////////
//                            teacherRepository.save(teacher);
//                            groupRepository.save(group);
//                            studentRepository.save(student);
//////////////////////////////////////
//                            students.add(new Student(
//                                    ordinalNumber,
//                                    name, surname,
//                                    patronymic, email,
//                                    phone, parentFullName,
//                                    parentEmail, parentPhone));
                        }
                    }
                }
            }
//        studentOfGroup.addAll(students);
        } catch (IOException e) {
            throw new IOException("Файл не соответствует шаблону");
//            return new ArrayList<>();
        } catch (IllegalArgumentException e) {
            //тут появляется ошибка когда при создании группы не добавляют файл
//            throw new IllegalArgumentException("Файл не соответствует шаблону");
        } catch (POIXMLException e){
            throw new POIXMLException("Файл не соответствует шаблону");
        } catch (Exception e){
            throw new RuntimeException("Файл не соответствует шаблону");
        }
        if (unionList.size() == 0 && data.length != 0) {
            throw new RuntimeException("Файл не соответствует шаблону");
        }
        return unionList;
    }

    public void saveGroup(Group group, long teacherId, byte[] bytes) throws IOException {
        List<GroupStudentTeacher> unionList = parseListStudent(bytes, group, teacherId);
        groupStudentTeacherRepository.saveAll(unionList);
//        groupRepository.findById(groupId).get().setStudents(students);
//        log.info("Saving list student for group {}", groupRepository.findById(groupId).get().getName());
//        groupRepository.save(groupRepository.findById(groupId).get());
//        studentRepository.saveAll(students);
    }


}
