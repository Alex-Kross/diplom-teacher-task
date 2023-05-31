package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.models.TeacherFile;
import com.bntu.diplom.teacherTask.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> listTeachers(String name) {
        if (name != null) return teacherRepository.findByName(name);
        return teacherRepository.findAll();
    }

    public void saveTeacher(Teacher teacher, MultipartFile file1) throws IOException {
        TeacherFile teacherFile = new TeacherFile();
//        teacherFile.setName(file1.getName());
        teacherFile.setFileName(file1.getOriginalFilename());
        teacherFile.setContentType(file1.getContentType());
        teacherFile.setSize(file1.getSize());
        teacherFile.setBytes(file1.getBytes());

        if (file1.getSize() != 0) {

            teacher.addTeacherFileToTeacher(teacherFile);
        }
        log.info("Saving new Teacher. Title: {}; Author: {}", teacher.getName(), teacher.getSurname());
        teacherRepository.save(teacher);
    }



    public void deleteTeacher(Long id) {

        teacherRepository.deleteById(id);
    }

    public Teacher getTeacherById(Long id) {

        return teacherRepository.findById(id).orElse(null);
    }
}
