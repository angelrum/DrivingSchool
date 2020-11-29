package ru.project.drivingschool.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.HasId;
import ru.project.drivingschool.repository.jpa.JpaKeyRepository;

import java.util.List;

@NoRepositoryBean
public abstract class AbstractKeyRepository<T extends HasId> {

    private JpaKeyRepository<T> repository;

    public AbstractKeyRepository(JpaKeyRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    public T get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.delete(id)!=0;
    }

    @Transactional
    public T save(T t) {
        if (!t.isNew() && !repository.existsById(t.id()))
            return null;
        return repository.save(t);
    }
}
