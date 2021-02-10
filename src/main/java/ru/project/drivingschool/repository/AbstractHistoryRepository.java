package ru.project.drivingschool.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.AbstractHistoryEntity;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.repository.jpa.JpaEmployeeRepository;
import ru.project.drivingschool.repository.jpa.JpaKeyRepository;

import java.time.LocalDateTime;
import java.util.Objects;

@NoRepositoryBean
public abstract class AbstractHistoryRepository<T extends AbstractHistoryEntity> extends AbstractRepository<T> {

    private JpaEmployeeRepository employeeRepository;

    public AbstractHistoryRepository(JpaKeyRepository<T> repository, JpaEmployeeRepository employeeRepository) {
        super(repository);
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public T save(T t, Long createdBy) {
        Employee created = (Objects.nonNull(createdBy) && createdBy > 0L) ? employeeRepository.getOne(createdBy) : null;
        LocalDateTime now = LocalDateTime.now();
        History h = t.getHistory();
        if (t.isNew()) {
            h.setCreatedBy(created);
            h.setCreatedOn(now);
        } else {
            h.setChangedBy(created);
            h.setChangedOn(now);
        }
        return super.save(t);
    }

}
