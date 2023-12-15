package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.entity.User;

import java.util.Optional;

@Repository
public interface TeacherRepository extends BaseRepository<Teacher> {

    Boolean existsByUser(User user);

    Optional<Teacher> findByUser(User user);

    Boolean existsByUserId(String userId);

    Optional<Teacher> findByUserId(String userId);

    Optional<Teacher> findByUserEmail(String email);
}
