package com.bntu.diplom.teacherTask.controllers;

import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
@RequestMapping("teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/")
    public String teachers(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("teachers", teacherService.listTeachers(name));
        return "teachers";
    }

    @GetMapping("/teacher/{id}")
    public String teacherInfo(@PathVariable Long id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        model.addAttribute("files", teacher.getTeacherFiles());
        return "teacher-info";
    }

    @PostMapping("/teacher/create")
    public String createTeacher(@RequestParam("file1") MultipartFile file1, Teacher teacher) throws IOException {
        teacherService.saveTeacher(teacher, file1);
        return "redirect:/teachers/";
    }

    @PostMapping("/teacher/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers/";
    }
}

