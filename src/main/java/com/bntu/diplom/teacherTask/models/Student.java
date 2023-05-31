package com.bntu.diplom.teacherTask.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
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
    private String numberCourse;
    private LocalDate dateAdmission;
    private String email;
    private String phone;
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
    private StudentGroup studentGroup;
}
