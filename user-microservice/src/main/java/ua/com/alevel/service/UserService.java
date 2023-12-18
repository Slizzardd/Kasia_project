package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.User;

import java.util.List;

public interface UserService extends BaseService<User> {

    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(String id);

    User findUserById(String id);

    User  findUserByEmail(String email);

    List<User> findAllUsers();

    void updatePassword(User user, String oldPassword, String newPassword);
}
