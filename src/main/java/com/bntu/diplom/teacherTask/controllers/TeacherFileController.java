package com.bntu.diplom.teacherTask.controllers;

import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.models.TeacherFile;
import com.bntu.diplom.teacherTask.repositories.TeacherFileRepository;
import com.bntu.diplom.teacherTask.services.TeacherFileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class TeacherFileController {
    private final TeacherFileRepository teacherFileRepository;
    private final TeacherFileService teacherFileService;

    @PostMapping("/file/upload")
    private String uploadFile(Teacher teacher, @RequestParam("file") MultipartFile file) throws IOException {
        teacherFileService.loadFile(teacher, file);
        return "distribution-task";
    }


//    @GetMapping("/images/{id}")
//    private ResponseEntity<?> getTeacherFileById(@PathVariable Long id) {
//        TeacherFile teacherFile = teacherFileRepository.findById(id).orElse(null);
//        return ResponseEntity.ok()
//                .header("fileName", teacherFile.getOriginalFileName())
//                .contentType(MediaType.valueOf(teacherFile.getContentType()))
//                .contentLength(teacherFile.getSize())
//                .body(new InputStreamResource(new ByteArrayInputStream(teacherFile.getBytes())));
//    }
}
