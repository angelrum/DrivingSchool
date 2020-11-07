package ru.project.drivingschool.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public abstract class AbstractRepository <T, N extends Number> {

    private JpaRepository<T, N> repository;

    public AbstractRepository(JpaRepository<T, N> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        return repository.findAll(Sort.by("created_on"));
    }

    public T getOne(N id) {
        return repository.getOne(id);
    }
}
