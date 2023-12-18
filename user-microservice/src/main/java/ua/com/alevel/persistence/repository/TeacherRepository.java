package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.User;


@Repository
public interface TeacherRepository extends BaseRepository<User> {
}
