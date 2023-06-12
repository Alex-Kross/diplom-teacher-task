package com.bntu.diplom.teacherTask.controllers;

import com.bntu.diplom.teacherTask.models.*;
import com.bntu.diplom.teacherTask.repositories.StudentRepository;
import com.bntu.diplom.teacherTask.repositories.TeacherGroupTopicRepository;
import com.bntu.diplom.teacherTask.repositories.TopicRepository;
import com.bntu.diplom.teacherTask.services.GroupService;
import com.bntu.diplom.teacherTask.services.TopicService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final TopicService topicService;
    private final StudentRepository studentRepository;
    private final TopicRepository topicRepository;
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
    public String groupInfo(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("group", groupService.getGroupById(id));
        List<GroupStudentTeacher> groupStudentTeachers = groupService.getGroupById(id).getGroupStudentTeachers();
        List<Student> students = new ArrayList<>();
        for (GroupStudentTeacher groupStudentTeacher: groupStudentTeachers) {
            if (groupStudentTeacher.getTeacher().getId() ==
                    groupService.getTeacherByPrincipal(principal).getId()) {
                students.add(groupStudentTeacher.getStudent());
            }
        }
        model.addAttribute("students", students);

        model.addAttribute("files", groupService.
                getTeacherByPrincipal(principal).getTeacherFiles());
        model.addAttribute("teacher", groupService.getTeacherByPrincipal(principal));

        topicRepository.deleteAll();
        count.clear();
        return "groups-info";
    }

    @GetMapping("/group/create-page")
    public String groupCreatePage(Model model, Principal principal){//, @ModelAttribute("group") Group group) {
//        List<Group> allGroup = groupService.getAllGroup();
        Teacher teacher = groupService.getTeacherByPrincipal(principal);
        model.addAttribute("groups", groupService.getAllGroup());
        model.addAttribute("files", teacher.getTeacherFiles());
        model.addAttribute("teacher", groupService.getTeacherByPrincipal(principal));
//        model.addAttribute("students", groupService.getGroupById(id).getStudents());
        return "group-edit";
    }
    @PostMapping("/group/create")
    public String createGroup(@RequestParam("listStudentFile") MultipartFile listStudentFile,
                              Group group, HttpServletRequest request, Principal principal  ) throws IOException {
//    public String createGroup(StudentGroup studentGroup) {
//        studentGroupService.saveGroup(studentGroup);
        Long idGroup = Long.parseLong(request.getParameter("select-group"));
        //TODO: нужно добавить в логику сохранения группы ФАЙЛ и подправить потом логику
        //TODO: в классе TeacherFileService

        Long idFile = Long.parseLong(request.getParameter("select-file"));

        groupService.saveGroup(idFile, idGroup, principal, group, listStudentFile);

        return "redirect:/";
    }

    @PostMapping("/group/delete/{id}")
    public String deleteGroup(@PathVariable Long id, Principal principal) {
        groupService.deleteGroup(id, principal);
        return "redirect:/";
    }
    @PostMapping("/group/{id}/dis")
    public String openDistribution(@PathVariable Long id, Principal principal, Model model) throws IOException {
        Teacher teacher = groupService.getTeacherByPrincipal(principal);
        List<GroupStudentTeacher> groupStudentTeachers = groupService.getGroupById(id).getGroupStudentTeachers();
        List<Student> students = new ArrayList<>();
        for (GroupStudentTeacher groupStudentTeacher: groupStudentTeachers) {
            if (Objects.equals(groupStudentTeacher.getTeacher().getId(), groupService.getTeacherByPrincipal(principal).getId())) {
                students.add(groupStudentTeacher.getStudent());
            }
        }
        model.addAttribute("teacher", groupService.getTeacherByPrincipal(principal));

        model.addAttribute("students", students);
        model.addAttribute("group", groupService.getGroupById(id));

        return "distribution-task";
    }

    @PostMapping("/group/{id}/dis/upload-topic")
    public String uploadTopicList(@PathVariable Long id,
                                  @RequestParam("listTopicFile") MultipartFile listTopicFile,
                                  Principal principal, Model model) throws IOException {

        Teacher teacher = groupService.getTeacherByPrincipal(principal);
        List<GroupStudentTeacher> groupStudentTeachers = groupService.getGroupById(id).getGroupStudentTeachers();
        List<Student> students = new ArrayList<>();
        for (GroupStudentTeacher groupStudentTeacher: groupStudentTeachers) {
            if (groupStudentTeacher.getTeacher().getId() ==
                    groupService.getTeacherByPrincipal(principal).getId()) {
                students.add(groupStudentTeacher.getStudent());
            }
        }
        model.addAttribute("teacher", groupService.getTeacherByPrincipal(principal));
        model.addAttribute("students", students);
        model.addAttribute("group", groupService.getGroupById(id));
        model.addAttribute("topics", topicService.saveTopics(listTopicFile));

        return "distribution-task";
    }

    private List<Topic> topics;
    private List<Student> students;
    private List<Integer> count;
    private final TeacherGroupTopicRepository teacherGroupTopicRepository;


    @PostMapping("/group/{id}/dis/com")
    public String composeTopicAndStudent(@PathVariable Long id,
                                         HttpServletRequest request,
                                  Principal principal, Model model) throws IOException {
        try {
            if (count.size() == 0) {
                count.add(0);
            }
            Long idStudent = Long.parseLong(request.getParameter("select-student"));
            Long idTopic = Long.parseLong(request.getParameter("select-topic").replace(",",""));

            Teacher teacher = groupService.getTeacherByPrincipal(principal);
            List<GroupStudentTeacher> groupStudentTeachers = groupService.getGroupById(id).getGroupStudentTeachers();
//            List<Topic> topics2 = topicRepository.findAll();
            List<TeacherGroupTopic> teacherGroupTopicList = topicService.composeStudentAndTopic(idStudent,
                        idTopic, teacher, groupStudentTeachers);

            if (count.get(0) == 0) {
                students = new ArrayList<>();
                for (GroupStudentTeacher groupStudentTeacher: groupStudentTeachers) {
                    if (groupStudentTeacher.getTeacher().getId() ==
                            teacher.getId()) {
                        students.add(groupStudentTeacher.getStudent());
                    }
                }
                topics = topicRepository.findAll();

            }
//            for (Topic topic: topics) {
//                if (!Objects.equals(topic.getId(), idTopic)) {
//                    topics.add(topic);
//                }
//            }
            //        topics = topicRepository.findAll();
            topics.removeIf(x -> Objects.equals(x.getId(), idTopic));
            students.removeIf(x -> x.getId() == idStudent);

            model.addAttribute("teacher", groupService.getTeacherByPrincipal(principal));
            model.addAttribute("students", students);
            model.addAttribute("group", groupService.getGroupById(id));
            model.addAttribute("topics", topics);
            model.addAttribute("composes", teacherGroupTopicList);
            count.set(0, count.get(0)+1);
            return "distribution-task";
        } catch (NumberFormatException e) {
            throw new RuntimeException("Вы не выбрали студента или тему");
        }
    }

    @PostMapping("/group/{id}/dis/com/task-list")
    public String composeTopicAndStudent(@PathVariable Long id, Model model, Principal principal){
        List<TeacherGroupTopic> teacherGroupTopicList = teacherGroupTopicRepository.findAll();

        model.addAttribute("teacher", groupService.getTeacherByPrincipal(principal));
        model.addAttribute("group", groupService.getGroupById(id));
        model.addAttribute("composes", teacherGroupTopicList);
        model.addAttribute("compose", teacherGroupTopicList.get(0));
        return "tasklist";
    }

    @PostMapping("/group/{id}/dis/com/task-list/upload")
    public String composeTaskList(@PathVariable Long id,
                                  @RequestParam("listTaskTemplate") MultipartFile listTaskTemplate,
                                  Model model,
                                  Principal principal) throws IOException {
        model.addAttribute("teacher", groupService.getTeacherByPrincipal(principal));
        model.addAttribute("group", groupService.getGroupById(id));
        List<TeacherGroupTopic> teacherGroupTopicList = topicService.composeListTask(listTaskTemplate, id);
        model.addAttribute("composes", teacherGroupTopicList);
        model.addAttribute("compose", teacherGroupTopicList.get(0));

        return "tasklist2";
//        return "redirect:/group/{id}/dis/com/task-list";
    }
}

