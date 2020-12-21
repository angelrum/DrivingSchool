package ru.project.drivingschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.repository.AbstractKeyHistoryRepository;
import java.util.List;

import static ru.project.drivingschool.util.ValidationUtil.*;

public abstract class AbstractService <T extends AbstractKeyHistoryEntity> {

    final Logger log = LoggerFactory.getLogger(getClass());

    private final AbstractKeyHistoryRepository<T> repository;

    public AbstractService(AbstractKeyHistoryRepository<T> repository) {
        this.repository = repository;
    }

    List<T> getAll() {
        log.info("Get all entity");
        return repository.getAll();
    }

    public T get(long id) {
        log.info("Get entity by id= {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public T create(T t, Long userId) {
        log.info("Create entity {}", t.toString());
        checkNotNull(t);
        checkNew(t);
        return repository.save(t, userId);
    }

    public T update(T t, Long userId) {
        log.info("Update entity {}", t.toString());
        checkNotNull(t);
        return checkNotFoundWithId(repository.save(t, userId), t.id());
    }

    public void delete(long id) {
        log.info("Delete entity by id={}", id);
        checknotfoundwithid(repository.delete(id), id);
    }
}
