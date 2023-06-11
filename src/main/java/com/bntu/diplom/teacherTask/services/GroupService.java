package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.*;
import com.bntu.diplom.teacherTask.repositories.*;
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
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final TeacherFileRepository teacherFileRepository;
    private final GroupStudentTeacherRepository groupStudentTeacherRepository;


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
    public void saveGroup(Long idFile, Long idGroup, Principal principal, Group group, MultipartFile listStudentFile)
            throws IOException, RuntimeException {
        // file object add to teacher principal

        Teacher teacher = getTeacherByPrincipal(principal);
//        if create new group
        if (idGroup == 0) {
            //if user doesn't fill all fields
            if (group.getName().equals("")
                    || group.getFacultyName().equals("")
                    || group.getUniversityName().equals("")) {
                throw new RuntimeException("Заполните все поля");
            }
            //  if create new file
            if (idFile == 0) {
                TeacherFile teacherFile = new TeacherFile(listStudentFile.getOriginalFilename(),
                        listStudentFile.getSize(),
                        listStudentFile.getContentType(),
                        listStudentFile.getBytes(),
                        FileType.STUDENT_LIST);
                if (listStudentFile.getSize() != 0) {
                    teacher.addTeacherFileToTeacher(teacherFile);
                }
            }
            List<Teacher> teachers = group.getTeachers();
            teachers.add(teacher);

            log.info("Saving new {} save teacher {}", group, getTeacherByPrincipal(principal));
            if (listStudentFile.getBytes().length == 0) {
                groupRepository.save(group);

            }
            if (idFile != 0) {
                //  if add exist file
                studentService.saveGroup(group, teacher.getId(),
                        teacherFileRepository.findById(idFile).get().getBytes());
            } else {
                //  if create new file
                studentService.saveGroup(group, teacher.getId(), listStudentFile.getBytes());
            }
        } else {
//            if add list student in exist group
            Group foundGroup = groupRepository.findById(idGroup).get();
            if (teacher.getGroups().contains(foundGroup)) {
                throw new RuntimeException("Такая группа у вас уже есть");
            }
            //  if create new file
            if (idFile == 0) {
                TeacherFile teacherFile = new TeacherFile(listStudentFile.getOriginalFilename(),
                        listStudentFile.getSize(),
                        listStudentFile.getContentType(),
                        listStudentFile.getBytes(),
                        FileType.STUDENT_LIST);
                if (listStudentFile.getSize() != 0) {
                    teacher.addTeacherFileToTeacher(teacherFile);
                }
            }
            List<Teacher> teachers = foundGroup.getTeachers();
            teachers.add(teacher);
            log.info("Saving new {} save teacher {}", foundGroup, getTeacherByPrincipal(principal));
            if (idFile != 0) {
                //  if add exist file
                studentService.saveGroup(foundGroup, teacher.getId(),
                        teacherFileRepository.findById(idFile).get().getBytes());
            } else {
                //  if create new file
                studentService.saveGroup(foundGroup, teacher.getId(), listStudentFile.getBytes());
            }
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
        List<GroupStudentTeacher> groupStudentTeachers = group.getGroupStudentTeachers();
        List<Long> listIndexForRemove = new ArrayList<>();
        List<Long> listIndexForRemove2 = new ArrayList<>();
        for (GroupStudentTeacher groupStudentTeacher: groupStudentTeachers) {
            if (groupStudentTeacher.getTeacher().getId() ==
                    getTeacherByPrincipal(principal).getId()) {
                listIndexForRemove.add(groupStudentTeacher.getId());
                listIndexForRemove2.add(groupStudentTeacher.getStudent().getId());
            }
        }
        groupStudentTeachers.removeIf(x -> x.getTeacher().getId() == getTeacherByPrincipal(principal).getId());
        for (Long idForRemove : listIndexForRemove) {
            groupStudentTeacherRepository.deleteById(idForRemove);
        }
        for (Long idForRemove : listIndexForRemove2) {
            studentRepository.deleteById(idForRemove);
        }
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
