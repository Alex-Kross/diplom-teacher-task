package com.bntu.diplom.teacherTask.controllers;

import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.spi.RegisterableService;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(Teacher teacher, Model model) {
        if (!registrationService.createUser(teacher)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + teacher.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }
}
