package com.bntu.diplom.teacherTask.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
    private String position;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private TemplateFile templateFile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "teacher")
    private List<TeacherFile> teacherFiles = new ArrayList<>();

    public void addTeacherFileToTeacher(TeacherFile teacherFile) {
        teacherFile.setTeacher(this);
        teacherFiles.add(teacherFile);
    }
}
