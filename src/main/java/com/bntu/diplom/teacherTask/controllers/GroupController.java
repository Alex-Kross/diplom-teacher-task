package com.bntu.diplom.teacherTask.controllers;

import com.bntu.diplom.teacherTask.models.Group;
import com.bntu.diplom.teacherTask.services.GroupService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;
//    private final ProductService productService;

//    @GetMapping("/")
//    public String groups(@RequestParam(name = "name", required = false) String name, Principal principal, Model model) {
////    public String groups(@RequestParam(name = "name", required = false) String name,  Model model) {
//        model.addAttribute("groups", groupService.listGroup(name));
//        model.addAttribute("teacher", groupService.getTeachersByPrincipal(principal));
//        return "groups";
//    }
    @GetMapping("/")
    public String groups(@RequestParam(name = "name", required = false) String name, Principal principal, Model model) {
//    public String groups(@RequestParam(name = "name", required = false) String name,  Model model) {
        model.addAttribute("groups", groupService.listGroup(name, principal));
        model.addAttribute("teacher", groupService.getTeacherByPrincipal(principal));
        return "groups";
    }
//    @GetMapping("/")
//    public String groups( Principal principal, Model model) {
////    public String groups(@RequestParam(name = "name", required = false) String name,  Model model) {
//        model.addAttribute("groups", groupService.listGroup(name));
//        model.addAttribute("teacher", groupService.getTeachersByPrincipal(principal));
//        return "groups";
//    }
//  here group yet certain teacher because request groups show only certain list groups
//    TODO:нужно сделать связь один к одному для группы и студента и реализовать тут загрузку студентов в таблицы
    @GetMapping("/group/{id}")
    public String groupInfo(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        model.addAttribute("students", groupService.getGroupById(id).getStudents());
        return "groups-info";
    }

    @GetMapping("/group/create-page")
    public String groupCreatePage(Model model){//, @ModelAttribute("group") Group group) {
        List<Group> allGroup = groupService.getAllGroup();
        model.addAttribute("groups", groupService.getAllGroup());
//        model.addAttribute("students", groupService.getGroupById(id).getStudents());
        return "group-edit";
    }
    @PostMapping("/group/create")
    public String createGroup(@RequestParam("listStudentFile") MultipartFile listStudentFile,
                              Group group, HttpServletRequest request, Principal principal  ) throws IOException {
//    public String createGroup(StudentGroup studentGroup) {
//        studentGroupService.saveGroup(studentGroup);
        Long idGroup = Long.parseLong(request.getParameter("select-group"));

        groupService.saveGroup(idGroup, principal, group, listStudentFile);

        return "redirect:/";
    }

    @PostMapping("/group/delete/{id}")
    public String deleteGroup(@PathVariable Long id, Principal principal) {
        groupService.deleteGroup(id, principal);
        return "redirect:/";
    }
}

