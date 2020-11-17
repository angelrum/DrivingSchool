package ru.project.drivingschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.project.drivingschool.model.HasId;
import ru.project.drivingschool.repository.AbstractRepository;
import java.util.List;

import static ru.project.drivingschool.util.ValidationUtil.*;

public abstract class AbstractService <T extends HasId> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final AbstractRepository<T> repository;

    public AbstractService(AbstractRepository<T> repository) {
        this.repository = repository;
    }

    List<T> getAll() {
        log.info("Get all entity");
        return repository.getAll();
    }

    T get(long id) {
        log.info("Get entity by id= {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    T create(T t) {
        log.info("Create entity {}", t.toString());
        checkNotNull(t);
        checkNew(t);
        return repository.save(t);
    }

    T update(T t) {
        log.info("Update entity {}", t.toString());
        checkNotNull(t);
        return checkNotFoundWithId(repository.save(t), t.id());
    }

    void delete(long id) {
        log.info("Delete entity by id={}", id);
        checknotfoundwithid(repository.delete(id), id);
    }
}
