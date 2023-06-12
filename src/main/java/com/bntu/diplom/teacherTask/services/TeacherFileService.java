package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.FileType;
import com.bntu.diplom.teacherTask.models.Group;
import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.models.TeacherFile;
import com.bntu.diplom.teacherTask.repositories.GroupRepository;
import com.bntu.diplom.teacherTask.repositories.TeacherFileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TeacherFileService {
    private final TeacherFileRepository teacherFileRepository;
    private final GroupRepository groupRepository;
    private final GroupService groupService;
    private final StudentService studentService;
    public void loadFile(Long idFile, Long idGroup, Principal principal, MultipartFile listStudentFile) throws IOException {
        try {
            Group foundGroup = groupRepository.findById(idGroup).get();
            Teacher teacher = groupService.getTeacherByPrincipal(principal);
            if (idFile == 0) {
                TeacherFile teacherFile = new TeacherFile(listStudentFile.getOriginalFilename(),
                        listStudentFile.getSize(),
                        listStudentFile.getContentType(),
                        listStudentFile.getBytes(),
                        FileType.STUDENT_LIST);
                if (listStudentFile.getSize() != 0) {
                    teacher.addTeacherFileToTeacher(teacherFile);
                }
            }
//        List<Teacher> teachers = foundGroup.getTeachers();
//        teachers.add(teacher);
            log.info("Saving new {} save teacher {}", foundGroup, groupService.getTeacherByPrincipal(principal));
            if (idFile != 0) {
                studentService.saveGroup(foundGroup, teacher.getId(),
                        teacherFileRepository.findById(idFile).get().getBytes());
            } else {
                studentService.saveGroup(foundGroup, teacher.getId(), listStudentFile.getBytes());
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("Вы не авторезированы");
        }
    }
}
