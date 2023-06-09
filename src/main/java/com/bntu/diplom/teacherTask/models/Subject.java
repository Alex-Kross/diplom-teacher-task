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
public class Subject {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="subject_id")
    private Long id;
    private String name;

    @ManyToMany( cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "teacher_subject",
            joinColumns = {
                    @JoinColumn(name = "subject_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "teacher_id")
            }
    )
    private List<Teacher> teachers = new ArrayList<>();
}
