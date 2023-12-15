package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Teacher;

import java.util.List;

public interface TeacherService extends BaseService<Teacher> {

    Teacher createTeacher(Teacher teacher);
    Teacher updateTeacher(Teacher teacher);
    void deleteTeacherById(String id);
    Teacher findTeacherById(String id);
    List<Teacher> findAllTeachers();
    Teacher findTeacherByUserEmail(String email);
}
