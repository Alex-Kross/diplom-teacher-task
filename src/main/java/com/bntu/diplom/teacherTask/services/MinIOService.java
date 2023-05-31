package com.bntu.diplom.teacherTask.services;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

    public byte[] uploadTemplateTaskList() throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        if (!init()) {
            throw new RuntimeException("Template in bucket not exists");
        }
        GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder()
            .bucket("file-teacher-task")
            .object("/template/task-list/List_zadania3.docx")
            .build());
        return object.readAllBytes();
    }

    public byte[] uploadTemplateTopic() throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        if (!init()) {
            throw new RuntimeException("Template in bucket not exists");
        }
        GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder()
                .bucket("file-teacher-task")
                .object("file-teacher-task/template/topics/темы курсовых.docx")
                .build());
        return object.readAllBytes();
    }
}
