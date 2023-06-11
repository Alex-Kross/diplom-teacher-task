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
public class GroupStudentTeacher {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private Teacher teacher;

    @JoinColumn(name = "student_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private Student student;

    @JoinColumn(name = "group_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private Group group;

    public GroupStudentTeacher(Teacher teacher, Student student, Group group) {
        this.teacher = teacher;
        this.student = student;
        this.group = group;
    }
}
