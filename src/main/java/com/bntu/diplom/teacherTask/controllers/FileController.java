package com.bntu.diplom.teacherTask.controllers;

import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.models.TeacherFile;
import com.bntu.diplom.teacherTask.models.TeacherGroupTopic;
import com.bntu.diplom.teacherTask.repositories.TeacherFileRepository;
import com.bntu.diplom.teacherTask.repositories.TeacherGroupTopicRepository;
import com.bntu.diplom.teacherTask.services.EmailSenderService;
import com.bntu.diplom.teacherTask.services.GroupService;
import com.bntu.diplom.teacherTask.services.MinIOService;
import com.bntu.diplom.teacherTask.services.TeacherFileService;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class FileController {
    private final TeacherFileRepository teacherFileRepository;
    private final TeacherFileService teacherFileService;
    private final GroupService groupService;
    private final EmailSenderService emailSenderService;

    private final TeacherGroupTopicRepository teacherGroupTopicRepository;
    private MinIOService minIOService = new MinIOService();
    @GetMapping("/files/{id}")
    private ResponseEntity<?> getFileById(@PathVariable Long id) throws ServerException,
            InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {

        TeacherFile file = teacherFileRepository.findById(id).orElse(null);
//        TeacherFile file = minIOService.uploadTemplateTaskList();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
//                .header("fileName", file.getOriginalFileName())
                .contentType(MediaType.valueOf(file.getContentType()))
                .contentLength(file.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(file.getBytes())));
    }

    @GetMapping("/download/files/{choice}")
    private ResponseEntity<?> getTemplateFileById(@PathVariable int choice) throws ServerException,
            InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        // choose what template download
        TeacherFile file = null;
        switch (choice) {
            case 1:
                file = minIOService.uploadTemplateStudentList();
                break;
            case 2:
                file = minIOService.uploadTemplateTopic();
                break;
            case 3:
                file = minIOService.uploadTemplateTaskList();
                break;
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
//                .header("fileName", file.getOriginalFileName())
                .contentType(MediaType.valueOf(file.getContentType()))
                .contentLength(file.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(file.getBytes())));
    }

    @GetMapping("/download/task-list/{id}")
    private ResponseEntity<?> getTaskListById(@PathVariable Long id) throws ServerException,
            InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        // choose what template download
//        TeacherFile file = null;

        TeacherGroupTopic file = teacherGroupTopicRepository.findById(id).get();

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
    @PostMapping("/group/{id}/file/upload")
    public String addStudentListFileToTeacher(@PathVariable Long id, Model model,
                                              HttpServletRequest request,
                                              @RequestParam("listStudentFile") MultipartFile listStudentFile,
                                              Principal principal) throws IOException {
        Long idFile = Long.parseLong(request.getParameter("select-file"));
        teacherFileService.loadFile(idFile, id, principal,listStudentFile);
//        model.addAttribute("group", groupService.getGroupById(id));
//        List<GroupStudentTeacher> groupStudentTeachers = groupService.getGroupById(id).getGroupStudentTeachers();
//        List<Student> students = new ArrayList<>();
//        for (GroupStudentTeacher groupStudentTeacher: groupStudentTeachers) {
//            if (groupStudentTeacher.getTeacher().getId() ==
//                    groupService.getTeacherByPrincipal(principal).getId()) {
//                students.add(groupStudentTeacher.getStudent());
//            }
//        }
//        model.addAttribute("students", students);
        return "redirect:/group/{id}";
    }

    @GetMapping("/send/task-lists")
    private void sendTaskListToStudents() throws MessagingException {
        List<TeacherGroupTopic> teacherGroupTopicList = teacherGroupTopicRepository.findAll();
        for (TeacherGroupTopic teacherGroupTopic : teacherGroupTopicList) {
            String surname = teacherGroupTopic.getGroupStudentTeacher().getTeacher().getSurname();
            String name = teacherGroupTopic.getGroupStudentTeacher().getTeacher().getName();
            String patronymic = teacherGroupTopic.getGroupStudentTeacher().getTeacher().getPatronymic();
            String email = teacherGroupTopic.getGroupStudentTeacher().getStudent().getEmail();
            emailSenderService.sendTskListToAllStudents(
                    email,
                    "Лист задания для курсовой работы. Преподаватель - " + surname + " " + name + " " + patronymic,
                    teacherGroupTopic);
        }
        //это сообщение об успещности
        throw new RuntimeException("Листы задания успешно отправлены");

    }

    public MinIOService getMinIOService() {
        return minIOService;
    }
}
