package com.bntu.diplom.teacherTask.repositories;

import com.bntu.diplom.teacherTask.models.StudentGroup;
import com.bntu.diplom.teacherTask.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends
        JpaRepository<Teacher, Long> {
    List<Teacher> findByName(String name);

    Teacher findByEmail(String email);
}
