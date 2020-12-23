package ru.project.drivingschool.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.repository.jpa.JpaKeyRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@NoRepositoryBean
public abstract class AbstractKeyHistoryRepository<T extends AbstractKeyHistoryEntity> {

    private JpaKeyRepository<T> repository;

    private JpaUserRepository userRepository;

    public AbstractKeyHistoryRepository(JpaKeyRepository<T> repository, JpaUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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
    public T save(T t, Long userId) {
        if (!t.isNew() && !repository.existsById(t.id()))
            return null;
        User user = Objects.nonNull(userId) ? userRepository.getOne(userId) : null;

        if (t.isNew()) {
            t.getHistory()
                    .setCreatedBy(user);
        } else {
            T old = repository.getOne(t.id());
            History h = old.getHistory();
            h.setChangedBy(user);
            h.setChangedOn(LocalDateTime.now());
            t.setHistory(h);
        }
        return repository.save(t);
    }

}
