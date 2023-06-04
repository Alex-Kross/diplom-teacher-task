package com.bntu.diplom.teacherTask.repositories;

import com.bntu.diplom.teacherTask.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends
        JpaRepository<Group, Long> {
    List<Group> findByName(String name);
}
