package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.types.Role;
import ua.com.alevel.persistence.types.Status;


@Repository
public interface TeacherRepository extends BaseRepository<User> {

    Boolean existsByIdAndRole(String id, Role role);
}
