package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.*;
import com.bntu.diplom.teacherTask.repositories.GroupRepository;
import com.bntu.diplom.teacherTask.repositories.TeacherGroupTopicRepository;
import com.bntu.diplom.teacherTask.repositories.TopicRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static jdk.nio.zipfs.ZipFileAttributeView.AttrID.group;

@Service
@Slf4j
@AllArgsConstructor
public class TopicService {
    private final GroupRepository groupRepository;
    private final TopicRepository topicRepository;
    private final TeacherGroupTopicRepository teacherGroupTopicRepository;
    public List<Topic> saveTopics(MultipartFile listTopicFile) throws IOException {
        List<Topic> topics = new ArrayList<>();
        try {
            byte[] data = listTopicFile.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            XWPFDocument document = new XWPFDocument(byteArrayInputStream);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            long var = 1;
            for (XWPFParagraph paragraph: paragraphs) {
                String date = paragraph.getText().trim().replace(".", "").replace("\n", " ");
                if (!date.isBlank()) {
                    topics.add(new Topic(var, date));
                }
                var++;
            }
            topicRepository.saveAll(topics);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return topics;
    }

    public List<TeacherGroupTopic> composeStudentAndTopic(Long idStudent, Long idTopic, Teacher teacher,
                                                          List<GroupStudentTeacher> groupStudentTeachers) {
        List<TeacherGroupTopic> teacherGroupTopicList = teacherGroupTopicRepository.findAll();
        TeacherGroupTopic composition = new TeacherGroupTopic();
        GroupStudentTeacher groupStudentTeacherCur = new GroupStudentTeacher();
        for (GroupStudentTeacher groupStudentTeacher: groupStudentTeachers) {
            if (groupStudentTeacher.getTeacher().getId() == teacher.getId()
                    && groupStudentTeacher.getStudent().getId() == idStudent) {
                groupStudentTeacherCur = groupStudentTeacher;
            }
        }
        composition.setGroupStudentTeacher(groupStudentTeacherCur);
        composition.setTopic(topicRepository.getById(idTopic));
        teacherGroupTopicList.add(composition);
        teacherGroupTopicRepository.saveAll(teacherGroupTopicList);
        return teacherGroupTopicList;
    }

    public List<TeacherGroupTopic> composeListTask(MultipartFile listTaskTemplate, Long groupId) throws IOException {
        List<TeacherGroupTopic> teacherGroupTopicList = teacherGroupTopicRepository.findAll();
        try  {
////        try (FileInputStream docFile = new FileInputStream("D:\\My\\university\\4corse\\Diplom\\примеры листов заданий\\zadanie_na_kursovoy_proekt10702119_1.docx")) {
            for (TeacherGroupTopic compose : teacherGroupTopicList) {
                Topic topic = compose.getTopic();
                Student student = compose.getGroupStudentTeacher().getStudent();
                Group group = groupRepository.findById(groupId).get();
                byte[] bytes = listTaskTemplate.getBytes();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                XWPFDocument document = new XWPFDocument(byteArrayInputStream);
                List<XWPFParagraph> paragraphs = document.getParagraphs();
                for (XWPFParagraph paragraph: paragraphs) {
                    for (XWPFRun run : paragraph.getRuns()) {
                        if(run.getText(0) != null){
                            if (run.getText(0).equals("{student}")) {
                                run.setText(student.getSurname()
                                        + " "
                                        + student.getName()
                                        + " "
                                        + student.getPatronymic(), 0);
                            }
                            if (run.getText(0).equals("{group}")) {
                                run.setText(group.getName(), 0);
                            }
                            if (run.getText(0).equals("{variant}")) {
                                run.setText(String.valueOf(topic.getVariant()), 0);
                            }
                            if (run.getText(0).equals("{topic}")) {
                                run.setText(topic.getTitle(), 0);
                            }
                        }
                    }
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                document.write(byteArrayOutputStream);
                byte[] newData = byteArrayOutputStream.toByteArray();
                compose.setContentType(listTaskTemplate.getContentType());
                compose.setBytes(newData);
                compose.setSize((long) newData.length);
                String[] split = listTaskTemplate.getOriginalFilename().split("\\.");
                String format = split[1];
                compose.setFileName(group.getName()+ " " +student.getSurname()+ " " +student.getName() + "." + format);
//                compose.setFileName("group " + group.getName()+ " student " +student.getOrdinalNumber() + "." + format);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        teacherGroupTopicRepository.saveAll(teacherGroupTopicList);
        return teacherGroupTopicList;
    }

}
