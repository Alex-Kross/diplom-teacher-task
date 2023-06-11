package com.bntu.diplom.teacherTask.repositories;

import com.bntu.diplom.teacherTask.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends
        JpaRepository<Topic, Long> {
}
