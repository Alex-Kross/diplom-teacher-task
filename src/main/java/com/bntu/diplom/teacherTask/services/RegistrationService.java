package com.bntu.diplom.teacherTask.services;

import com.bntu.diplom.teacherTask.models.Role;
import com.bntu.diplom.teacherTask.models.Teacher;
import com.bntu.diplom.teacherTask.repositories.TeacherRepository;
import com.bntu.diplom.teacherTask.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationService {
    private final TeacherRepository teacherRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean createUser(Teacher teacher) {
        String userEmail = teacher.getEmail();
        if (teacherRepository.findByEmail(userEmail) != null) return false;
        teacher.setActive(true);
        teacher.setUserRole(Role.USER);

        teacher.setPassword(bCryptPasswordEncoder
                .encode(teacher.getPassword()));
        log.info("Saving new User with email: {}", userEmail);
        teacherRepository.save(teacher);
        return true;
    }
}
