package ru.project.drivingschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.repository.AbstractKeyHistoryRepository;
import ru.project.drivingschool.to.ResultPage;
import ru.project.drivingschool.model.Index;

import java.util.List;
import java.util.Objects;

import static ru.project.drivingschool.model.Index.*;
import static ru.project.drivingschool.util.ValidationUtil.*;

public abstract class AbstractService <T extends AbstractKeyHistoryEntity> {

    final Logger log = LoggerFactory.getLogger(getClass());

    private final AbstractKeyHistoryRepository<T> repository;

    public AbstractService(AbstractKeyHistoryRepository<T> repository) {
        this.repository = repository;
    }

    public ResultPage<T> getAll(Boolean active) {
        if (Objects.nonNull(active))
            return this.getAll(Example.of(getExample(active)));
        else
            return this.getAll();
    }

    public ResultPage<T> getAll(int offset, int limit, Boolean active) {
        if (Objects.nonNull(active))
            return this.getAll(offset, limit, Example.of(getExample(active)));
        else
            return this.getAll(offset, limit);
    }
    /* Start GET_ALL methods*/
    public ResultPage<T> getAll() {
        log.info("Get all entity with default limit");
        return this.getAll(DEF_PAGE, DEF_LIMIT);
    }

    public ResultPage<T> getAll(int offset, int limit) {
        log.info("Get all entity from page={} and limit={}", offset, limit);
        return convertFromPage(repository.getAll(PageRequest.of(offset, limit)));
    }

    protected ResultPage<T> getAll(int offset, int limit, Example<T> example) {
        log.info("Get all entity from page={} and limit={} and query parameter", offset, limit);
        Page<T> p = repository.getAll(example, PageRequest.of(offset, limit));
        return convertFromPage(p);
    }

    protected ResultPage<T> getAll(Example<T> example) {
        log.info("Get all entity with default limit and query parameter");
        return this.getAll(DEF_PAGE, DEF_LIMIT, example);
    }
    /* End GET_ALL methods*/

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

    protected <E> ResultPage<E> convertFromPage(Page<E> page) {
        Index index = new Index(page.getNumberOfElements(), page.getNumber(), page.getTotalPages());
        List<E> list = checkNotFound(page.toList());
        return new ResultPage<E>(index, list);
    }

    /* Abstract methods */
    abstract T getExample(boolean active);
}
