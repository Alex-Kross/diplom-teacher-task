package com.bntu.diplom.teacherTask.controllers;

import com.bntu.diplom.teacherTask.models.StudentGroup;
import com.bntu.diplom.teacherTask.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;
//    private final ProductService productService;

    @GetMapping("/")
    public String groups(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("groups", groupService.listGroup(name));
        return "groups";
    }

    @GetMapping("/group/{id}")
    public String groupInfo(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "groups-info";
    }

    @PostMapping("/group/create")
    public String createGroup(StudentGroup studentGroup) {
        groupService.saveGroup(studentGroup);
        return "redirect:/";
    }

    @PostMapping("/group/delete/{id}")
    public String deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return "redirect:/";
    }
}

