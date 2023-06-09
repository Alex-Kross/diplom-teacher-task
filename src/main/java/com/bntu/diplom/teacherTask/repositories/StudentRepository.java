package com.bntu.diplom.teacherTask.repositories;

import com.bntu.diplom.teacherTask.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Override
    List<Student> findAll();

    //    List<Student> findByGroup(String name);
}
