package com.bntu.diplom.teacherTask.models;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
public class TeacherFile {
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
    private FileType fileType;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER )
//    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public TeacherFile(String fileName, Long size, String contentType, byte[] bytes, FileType fileType) {
        this.fileName = fileName;
        this.size = size;
        this.contentType = contentType;
        this.bytes = bytes;
        this.fileType = fileType;
    }
}
