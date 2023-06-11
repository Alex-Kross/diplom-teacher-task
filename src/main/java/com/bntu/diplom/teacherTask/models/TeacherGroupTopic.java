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

    @JoinColumn(name = "teacher_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private Teacher teacher;

    @JoinColumn(name = "topic_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private Topic topic;

    @JoinColumn(name = "group_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private Group group;

    @JoinColumn(name = "teacher_file_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private TeacherFile teacherFile;

    @JoinColumn(name = "student_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private Student student;
}
