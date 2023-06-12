package com.bntu.diplom.teacherTask.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Teacher implements UserDetails {
    @Id
    @GeneratedValue(

            strategy = GenerationType.IDENTITY
    )
    @Column(name="teacher_id")
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
    private String position;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Role userRole;
// relation with template file
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    private TemplateFile templateFile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teacher")
    private List<TeacherFile> teacherFiles = new ArrayList<>();


//    @OneToMany( cascade =
//            {
//                    CascadeType.DETACH,
//                    CascadeType.MERGE,
//                    CascadeType.REFRESH,
//                    CascadeType.PERSIST
//            })
//    @JoinTable(name = "teacher_student_group",
//            joinColumns = {
//                    @JoinColumn(name = "teacher_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "group_id")
//            }
//    )
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teacher")
    private List<GroupStudentTeacher> groupStudentTeachers = new ArrayList<>();





//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teacher_id")
//    private List<TeacherGroup> teacherGroups = new ArrayList<>();

//    @ManyToMany(mappedBy = "teachers", cascade = {CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.REFRESH,
//            CascadeType.PERSIST})
    @ManyToMany( cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "teacher_student_group",
            joinColumns = {
                    @JoinColumn(name = "teacher_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "group_id")
            }
    )
    private List<Group> groups = new ArrayList<>();


//    subject relation

//    @ManyToMany( cascade =
//            {
//                    CascadeType.DETACH,
//                    CascadeType.MERGE,
//                    CascadeType.REFRESH,
//                    CascadeType.PERSIST
//            })
//
//    @JoinTable(name = "teacher_subject",
//            joinColumns = {
//                    @JoinColumn(name = "teacher_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "subject_id")
//            }
//    )
//    private List<Subject> subjects = new ArrayList<>();

    public void addTeacherFileToTeacher(TeacherFile teacherFile) {
        teacherFile.setTeacher(this);
        teacherFiles.add(teacherFile);
    }

    public void addUnion(GroupStudentTeacher groupStudentTeacher) {
        groupStudentTeacher.setTeacher(this);
        groupStudentTeachers.add(groupStudentTeacher);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
