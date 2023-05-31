package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.StudentGroup;
import com.bntu.diplom.teacherTask.repositories.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public List<StudentGroup> listGroup(String name) {
        if (name != null ) return groupRepository.findByName(name);
        return groupRepository.findAll();
    }

    public void saveGroup(StudentGroup studentGroup) {
        log.info("Saving new {}", studentGroup);
        groupRepository.save(studentGroup);
    }

    public void deleteGroup(Long id) {

        groupRepository.deleteById(id);
    }

    public StudentGroup getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }
}
