package com.bntu.diplom.teacherTask.repositories;

import com.bntu.diplom.teacherTask.models.GroupStudentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupStudentTeacherRepository extends
        JpaRepository<GroupStudentTeacher, Long> {

}
