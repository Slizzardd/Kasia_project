package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EntityExistException;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.repository.TeacherRepository;
import ua.com.alevel.service.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        if (!teacherRepository.existsById(teacher.getId())) {
            return teacherRepository.save(teacher);
        } else {
            throw new EntityExistException("");
        }
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(String id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher findTeacherById(String id) {
        return teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findTeacherByUserEmail(String email) {
        return teacherRepository.findByUserEmail(email).orElse(null);
    }
}
