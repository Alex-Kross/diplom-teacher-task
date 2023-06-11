package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.FileType;
import com.bntu.diplom.teacherTask.models.TeacherFile;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Bucket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Getter
@Setter
@Slf4j

//@Service
//@AllArgsConstructor
public class MinIOService {

    private MinioClient minioClient;



    public boolean init() throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException, XmlParserException,
            InternalException {
        minioClient =
                MinioClient.builder()
                        .endpoint("https://play.min.io")
                        .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
                        .build();
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket("file-teacher-task").build());
    }

    public TeacherFile uploadTemplateTaskList() throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        if (!init()) {
            throw new RuntimeException("Template in bucket not exists");
        }
        GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder()
                .bucket("file-teacher-task")
                .object("template/task-list/List_zadania3.docx")
                .build());
        Headers headers = object.headers();
        String contentType = headers.get("Content-Type");
        Long sizeFile = Long.parseLong(headers.get("Content-Length"));
        String[] split = object.object().split("/");
        String typeFile = split[split.length - 1].split("\\.")[1];
        String nameFile = "Template list of task."+typeFile;

        byte[] bytes = object.readAllBytes();
        return new TeacherFile(nameFile, sizeFile, contentType, bytes, FileType.TASK_LIST);
    }

    public TeacherFile uploadTemplateTopic() throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        if (!init()) {
            throw new RuntimeException("Template in bucket not exists");
        }
        GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder()
                .bucket("file-teacher-task")
                .object("template/topics/темы курсовых.docx")
                .build());
        Headers headers = object.headers();
        String contentType = headers.get("Content-Type");
        Long sizeFile = Long.parseLong(headers.get("Content-Length"));
        String[] split = object.object().split("/");
        String typeFile = split[split.length - 1].split("\\.")[1];
        String nameFile = "Template topics."+typeFile;

        byte[] bytes = object.readAllBytes();
        return new TeacherFile(nameFile, sizeFile, contentType, bytes, FileType.TOPIC_LIST);
    }

    public TeacherFile uploadTemplateStudentList() throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        if (!init()) {
            throw new RuntimeException("Template in bucket not exists");
        }
        GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder()
                .bucket("file-teacher-task")
                .object("template/student-list/listStudents3.xlsx")
                .build());

        Headers headers = object.headers();
        String contentType = headers.get("Content-Type");
        Long sizeFile = Long.parseLong(headers.get("Content-Length"));
        String[] split = object.object().split("/");
        String typeFile = split[split.length - 1].split("\\.")[1];
        String nameFile = "Template list students."+typeFile;

        byte[] bytes = object.readAllBytes();
        return new TeacherFile(nameFile, sizeFile, contentType, bytes, FileType.STUDENT_LIST);
    }

}
