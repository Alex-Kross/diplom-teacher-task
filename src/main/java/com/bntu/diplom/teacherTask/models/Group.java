package com.bntu.diplom.teacherTask.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "student_group")
public class Group {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="group_id")
    private Long id;
    private String name;
    private String facultyName;
    private String universityName;
//    private String subject;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teacher")
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
//    private List<Student> students = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group_id")
//    private List<TeacherGroup> teacherGroups = new ArrayList<>();

    @ManyToMany( cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "teacher_student_group",
            joinColumns = {
                    @JoinColumn(name = "group_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "teacher_id")
            }
    )
    private List<Teacher> teachers = new ArrayList<>();

//    @ManyToMany( cascade =
//            {
//                    CascadeType.DETACH,
//                    CascadeType.MERGE,
//                    CascadeType.REFRESH,
//                    CascadeType.PERSIST
//            })
//    @JoinTable(name = "student_student_group",
//            joinColumns = {
//                    @JoinColumn(name = "group_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "student_id")
//            }
//    )
//    private List<Student> students = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private List<GroupStudentTeacher> groupStudentTeachers = new ArrayList<>();



    public void addUnion(GroupStudentTeacher groupStudentTeacher) {
        groupStudentTeacher.setGroup(this);
        groupStudentTeachers.add(groupStudentTeacher);
    }
}
