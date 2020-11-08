package ru.project.drivingschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.AbstractNamedEntity;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.repository.jpa.JpaEmployeeRepository;
import ru.project.drivingschool.repository.jpa.JpaNamedRepository;

import java.time.LocalDateTime;

@NoRepositoryBean
public abstract class AbstractNamedHistoryRepository <T extends AbstractNamedEntity> extends AbstractRepository<T> {

    private JpaNamedRepository<T> repository;

    private JpaEmployeeRepository employeeRepository;

    public AbstractNamedHistoryRepository(JpaNamedRepository<T> repository, JpaEmployeeRepository employeeRepository) {
        super(repository);
        this.repository = repository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public T save(T t, Long createdBy) {
        Employee created = createdBy > 0 ? employeeRepository.getOne(createdBy) : null;
        LocalDateTime now = LocalDateTime.now();
        if (t.isNew()) {
            t.setCreatedBy(created);
            t.setCreatedOn(now);
        } else {
            t.setChangedBy(created);
            t.setChangedOn(now);
        }
        return repository.save(t);
    }

    public T getByPhone(String phone) {
        return repository.getFirstByPhone(phone);
    }

}
