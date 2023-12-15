package ua.com.alevel.service.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EntityExistException;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.repository.UserRepository;
import ua.com.alevel.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        if (!checkExistUserEmail(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new EntityExistException("User already exist with email: " + user.getEmail());
        }
    }

    @Override
    public User updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User does not found with id: " + user.getId());
        }
    }

    @Override
    public void deleteUserById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updatePassword(User user, String oldPassword, String newPassword) {
        if (checkMatchPasswords(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }else {
            throw new BadCredentialsException("Password does not matched");
        }
    }

    private Boolean checkExistUserEmail(User user) {
        return userRepository.existsByEmail(user.getEmail());
    }

    private Boolean checkMatchPasswords(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
