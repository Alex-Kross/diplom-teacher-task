package com.bntu.diplom.teacherTask.models;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

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
            String parentEmail, String parentPhone, Group group) {

        this.ordinalNumber = ordinalNumber;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.parentFullName = parentFullName;
        this.parentEmail = parentEmail;
        this.parentPhone = parentPhone;
        this.group = group;
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
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Group group;
}
