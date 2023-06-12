package com.bntu.diplom.teacherTask.controllers;

import com.bntu.diplom.teacherTask.models.Group;
import com.bntu.diplom.teacherTask.models.GroupStudentTeacher;
import com.bntu.diplom.teacherTask.models.Student;
import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.repositories.TeacherFileRepository;
import com.bntu.diplom.teacherTask.services.GroupService;
import com.bntu.diplom.teacherTask.services.TeacherFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class TopicController {
    private final TeacherFileRepository teacherFileRepository;
    private final TeacherFileService teacherFileService;
    private final GroupService groupService;
    @GetMapping("/dis")
    public String openDistribution(Principal principal, Model model) throws IOException {
        Teacher teacher = groupService.getTeacherByPrincipal(principal);
        model.addAttribute("groups", teacher.getGroups());

        return "distribution-task";
    }

//    @PostMapping("/dis/upload-student")
    public String uploadStudentList(HttpServletRequest request, Principal principal, Model model) throws IOException {
        Teacher teacher = groupService.getTeacherByPrincipal(principal);
        model.addAttribute("groups", teacher.getGroups());
        Long idGroup = Long.parseLong(request.getParameter("select-group"));
        List<GroupStudentTeacher> groupStudentTeachers = groupService
                .getGroupById(idGroup)
                .getGroupStudentTeachers();
        List<Student> students = new ArrayList<>();
        for (GroupStudentTeacher groupStudentTeacher: groupStudentTeachers) {
            if (groupStudentTeacher.getTeacher().getId() ==
                    groupService.getTeacherByPrincipal(principal).getId()) {
                students.add(groupStudentTeacher.getStudent());
            }
        }
        model.addAttribute("students", students);
        return "distribution-task";
    }

//    @PostMapping("/dis/upload-topic")
    public String uploadTopicList(HttpServletRequest request, Principal principal, Model model) throws IOException {
        Teacher teacher = groupService.getTeacherByPrincipal(principal);
        model.addAttribute("groups", teacher.getGroups());
        Long idGroup = Long.parseLong(request.getParameter("select-group"));
        List<GroupStudentTeacher> groupStudentTeachers = groupService
                .getGroupById(idGroup)
                .getGroupStudentTeachers();
        List<Student> students = new ArrayList<>();
        for (GroupStudentTeacher groupStudentTeacher: groupStudentTeachers) {
            if (groupStudentTeacher.getTeacher().getId() ==
                    groupService.getTeacherByPrincipal(principal).getId()) {
                students.add(groupStudentTeacher.getStudent());
            }
        }
        model.addAttribute("students", students);
        return "distribution-task";
    }
}
