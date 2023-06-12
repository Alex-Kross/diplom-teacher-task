package com.bntu.diplom.teacherTask.repositories;

import com.bntu.diplom.teacherTask.models.TeacherFile;
import com.bntu.diplom.teacherTask.models.TeacherGroupTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherGroupTopicRepository extends JpaRepository<TeacherGroupTopic, Long> {
}
