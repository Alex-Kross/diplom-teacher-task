package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.models.TeacherFile;
import com.bntu.diplom.teacherTask.repositories.TeacherFileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@AllArgsConstructor
public class TeacherFileService {
    TeacherFileRepository teacherFileRepository;
    public void loadFile(Teacher teacher, MultipartFile file) throws IOException {
//        TeacherFile teacherFile = new TeacherFile();
//        teacherFile.setName(file.getName());
//        teacherFile.setOriginalFileName(file.getOriginalFilename());
//        teacherFile.setContentType(file.getContentType());
//        teacherFile.setSize(file.getSize());
//        teacherFile.setBytes(file.getBytes());
//        teacherFile.setTeacher(teacher);
//
//        teacher.addTeacherFileToTeacher(teacherFile);
//        teacherFileRepository.save(teacherFile);
    }
}
