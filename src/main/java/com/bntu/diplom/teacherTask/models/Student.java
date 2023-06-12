package com.bntu.diplom.teacherTask.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="student_id")
    private Long id;
    private Long ordinalNumber;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String phone;
    private String parentFullName;
    private String parentEmail;
    private String parentPhone;

    public Student(
            Long ordinalNumber, String name,
            String surname, String patronymic,
            String email, String phone, String parentFullName,
            String parentEmail, String parentPhone) {
        this.ordinalNumber = ordinalNumber;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.parentFullName = parentFullName;
        this.parentEmail = parentEmail;
        this.parentPhone = parentPhone;
    }

    //    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @ManyToOne
//    @JoinColumn(
//            name = "student_group_id",
//            nullable = false,
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "student_group_fk"
//            )
//    )
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    private Group group;


//    @ManyToMany( cascade =
//            {
//                    CascadeType.DETACH,
//                    CascadeType.MERGE,
//                    CascadeType.REFRESH,
//                    CascadeType.PERSIST
//            })
//    @JoinTable(name = "student_student_group",
//            joinColumns = {
//                    @JoinColumn(name = "student_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "group_id")
//            }
//    )
//    private List<Group> groups = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private List<GroupStudentTeacher> groupStudentTeachers = new ArrayList<>();


    public void addUnion(GroupStudentTeacher groupStudentTeacher) {
        groupStudentTeacher.setStudent(this);
        groupStudentTeachers.add(groupStudentTeacher);
    }
}
