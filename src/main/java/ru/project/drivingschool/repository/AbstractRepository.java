package ru.project.drivingschool.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoRepositoryBean
public abstract class AbstractRepository <T, N extends Number> {

    private JpaRepository<T, N> repository;

    public AbstractRepository(JpaRepository<T, N> repository) {
        this.repository = repository;
    }

    List<T> getAll() {
        return repository.findAll(Sort.by("createdOn"));
    }

    public T get(N id) {
        return repository.getOne(id);
    }

    @Transactional
    public void delete(N id) {
        repository.deleteById(id);
    }

    @Transactional
    public T save(T t) {
        return repository.save(t);
    }
}
