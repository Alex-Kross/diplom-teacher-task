package com.bntu.diplom.teacherTask.models;

import com.bntu.diplom.teacherTask.services.MinIOService;
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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class TemplateFile {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String fileName;
    private Long size;
    private String contentType;
    @Lob
    private byte[] file;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "templateFile")
    private List<Teacher> teachers = new ArrayList<>();

    @PrePersist
    private void init(){
//        MinIOService minIOService = new MinIOService();
//        minIOService.
    }
}
