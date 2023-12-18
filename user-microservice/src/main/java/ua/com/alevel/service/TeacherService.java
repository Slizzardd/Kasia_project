package ua.com.alevel.service;

import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.persistence.entity.User;

public interface TeacherService extends BaseService<User> {

    User createTeacher(User user);

    User updateTeacher(User user);

    User findTeacherById(String id);
}
