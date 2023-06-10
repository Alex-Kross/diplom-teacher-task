package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.Subject;
import com.bntu.diplom.teacherTask.models.Teacher;
//import com.bntu.diplom.teacherTask.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SubjectService {
//    private final SubjectRepository subjectRepository;
    private final GroupService groupService;
    public void saveSubject(Principal principal, Subject subject) throws IOException {
//        List<Teacher> teachers = subject.getTeachers();
        Teacher teacher = groupService.getTeacherByPrincipal(principal);
//        teachers.add(teacher);
//        log.info("Saving new {} save subject {}", subject.getName(), teacher.getName());
//        subjectRepository.save(subject);
    }
}
