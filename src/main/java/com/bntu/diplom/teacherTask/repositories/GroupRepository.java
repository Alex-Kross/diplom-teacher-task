package com.bntu.diplom.teacherTask.repositories;

import com.bntu.diplom.teacherTask.models.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends
        JpaRepository<StudentGroup, Long> {
    List<StudentGroup> findByName(String name);
}
