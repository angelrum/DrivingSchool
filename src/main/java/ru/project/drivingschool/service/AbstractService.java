package ru.project.drivingschool.service;

import ru.project.drivingschool.model.HasId;
import ru.project.drivingschool.repository.AbstractRepository;
import java.util.List;

import static ru.project.drivingschool.util.ValidationUtil.*;

public abstract class AbstractService <T extends HasId> {

    private final AbstractRepository<T> repository;

    public AbstractService(AbstractRepository<T> repository) {
        this.repository = repository;
    }

    List<T> getAll() {
        return repository.getAll();
    }

    T get(long id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    T create(T t) {
        checkNotNull(t);
        checkNew(t);
        return repository.save(t);
    }

    T update(T t) {
        checkNotNull(t);
        return checkNotFoundWithId(repository.save(t), t.id());
    }

    void delete(long id) {
        checknotfoundwithid(repository.delete(id), id);
    }
}
