package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.repository.TeacherRepository;
import ua.com.alevel.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public User createTeacher(User user) {
        return teacherRepository.save(user);
    }

    @Override
    public User updateTeacher(User user) {
        return teacherRepository.save(user);
    }

    @Override
    public User findTeacherById(String id) {
        return teacherRepository.findById(id).orElse(null);
    }
}
