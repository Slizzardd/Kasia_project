package ua.com.alevel.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.alevel.persistence.entity.BaseEntity;

public interface BaseRepository<T extends BaseEntity> extends MongoRepository<T, String> {
}
