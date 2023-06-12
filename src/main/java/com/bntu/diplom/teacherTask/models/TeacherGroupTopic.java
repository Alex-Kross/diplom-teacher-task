package com.bntu.diplom.teacherTask.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class TeacherGroupTopic {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String fileName;
    private Long size;
    private String contentType;
    @Lob
    private byte[] bytes;
    @Enumerated(EnumType.STRING)

    @JoinColumn(name = "topic_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private Topic topic;

    @JoinColumn(name = "group_student_teacher_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private GroupStudentTeacher groupStudentTeacher;




}
