package com.bntu.diplom.teacherTask.controllers;

import com.bntu.diplom.teacherTask.models.Subject;
import com.bntu.diplom.teacherTask.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

//    @PostMapping("/group/{id}/subject/create")
//    public String createSubject(Subject subject, Principal principal) throws IOException {
////    public String createGroup(StudentGroup studentGroup) {
////        studentGroupService.saveGroup(studentGroup);
//        subjectService.saveSubject(principal, subject);
//        return "groups-info";
//    }
}
