package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.Group;
import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.repositories.GroupRepository;
import com.bntu.diplom.teacherTask.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

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
                    if (teacher.getName().equals(getTeachersByPrincipal(principal).getName())) {
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
    public void saveGroup(Principal principal, Group group) {
//        studentGroupRepository.findByName(getTeachersByPrincipal(principal).getName())
        List<Teacher> teachers = group.getTeachers();
        teachers.add(getTeachersByPrincipal(principal));

//        studentGroup.setTeachers(getTeachersByPrincipal(principal));
        log.info("Saving new {} save teacher {}", group, getTeachersByPrincipal(principal));
//        log.info("Saving new {} save teacher {}", studentGroup);
        groupRepository.save(group);
    }

    public Teacher getTeachersByPrincipal(Principal principal) {
//        List<Teacher> teachers = teacherRepository.findAll();
//        if (teachers.isEmpty()) {
//            teachers.add(new Teacher());
//        }
        if (principal == null) return new Teacher();
        Teacher teacher = teacherRepository.findByEmail(principal.getName());
//        teachers.add(teacher);
        return teacher;
    }

    public void deleteGroup(Long id) {

        groupRepository.deleteById(id);
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }
}
