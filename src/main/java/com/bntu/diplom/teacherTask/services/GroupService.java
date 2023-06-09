package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.Group;
import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.models.TeacherFile;
import com.bntu.diplom.teacherTask.repositories.GroupRepository;
import com.bntu.diplom.teacherTask.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentService studentService;


//    public List<Group> listGroup(String name) {
//        if (name != null ) return groupRepository.findByName(name);
//        return groupRepository.findAll();
//    }
    public List<Group> listGroup(String name, Principal principal) {
        if (name != null ) return groupRepository.findByName(name);
        if (principal != null) {
            List<Group> listGroup = new ArrayList<>();
            for (Group group : groupRepository.findAll()) {
                for (Teacher teacher : group.getTeachers()) {
                    if (teacher.getName().equals(getTeacherByPrincipal(principal).getName())) {
                        listGroup.add(group);
                        break;
                    }
                }
            }
            return listGroup;
        }
        return new ArrayList<>();
    }
//    public List<Group> listGroupByTeacher(String teacherName) {
//        groupRepository.
//        if (name != null ) return groupRepository.findByName(name);
//        return groupRepository.find();
//    }

//    public void saveGroup(StudentGroup studentGroup) {
    public void saveGroup(Long idGroup, Principal principal, Group group, MultipartFile listStudentFile) throws IOException {
        // create file object
        TeacherFile teacherFile = new TeacherFile();
        teacherFile.setFileName(listStudentFile.getOriginalFilename());
        teacherFile.setContentType(listStudentFile.getContentType());
        teacherFile.setSize(listStudentFile.getSize());
        teacherFile.setBytes(listStudentFile.getBytes());
        // file object add to teacher principal
        Teacher teacher = getTeacherByPrincipal(principal);
        if (listStudentFile.getSize() != 0) {

            teacher.addTeacherFileToTeacher(teacherFile);
        }

        if (idGroup == 0) {
            List<Teacher> teachers = group.getTeachers();
            teachers.add(teacher);

            log.info("Saving new {} save teacher {}", group, getTeacherByPrincipal(principal));
            groupRepository.save(group);
            studentService.saveGroup(group.getId(), listStudentFile.getBytes());
        } else {
            Group foundGroup = groupRepository.findById(idGroup).get();
            List<Teacher> teachers = foundGroup.getTeachers();
            teachers.add(teacher);
            log.info("Saving new {} save teacher {}", foundGroup, getTeacherByPrincipal(principal));
            studentService.saveGroup(foundGroup.getId(), listStudentFile.getBytes());
        }
    }

    public Teacher getTeacherByPrincipal(Principal principal) {
//        List<Teacher> teachers = teacherRepository.findAll();
//        if (teachers.isEmpty()) {
//            teachers.add(new Teacher());
//        }
        if (principal == null) return new Teacher();
        Teacher teacher = teacherRepository.findByEmail(principal.getName());
//        teachers.add(teacher);
        return teacher;
    }

    public void deleteGroup(Long id, Principal principal) {
        Group group = groupRepository.findById(id).get();
        List<Teacher> teachers = group.getTeachers();
        // if group has only one teacher
        if (teachers.size() < 2) {
            groupRepository.deleteById(id);
            // else if group has more one teacher
        } else {
            teachers.remove(getTeacherByPrincipal(principal));
            groupRepository.save(group);
        }
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public List<Group> getAllGroup(){
        return groupRepository.findAll();
    }
}
