package com.bntu.diplom.teacherTask.controllers;

import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.models.TeacherFile;
import com.bntu.diplom.teacherTask.repositories.TeacherFileRepository;
import com.bntu.diplom.teacherTask.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final TeacherFileRepository teacherFileRepository;
    private final GroupService groupService;
    @GetMapping("/files/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        TeacherFile file = teacherFileRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
//                .header("fileName", file.getOriginalFileName())
                .contentType(MediaType.valueOf(file.getContentType()))
                .contentLength(file.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(file.getBytes())));
    }

    @PostMapping("/group/{id}/upload")
    public String createTeacher(@PathVariable Long id, Model model, @RequestParam("file1") MultipartFile file1, Teacher teacher) throws IOException {
//        teacherService.saveTeacher(teacher, file1);
        model.addAttribute("group", groupService.getGroupById(id));
        return "groups-info";
    }
}
