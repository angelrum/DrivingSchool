package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.project.drivingschool.model.AbstractNamedEntity;

@NoRepositoryBean
public interface JpaNamedRepository <T extends AbstractNamedEntity> extends JpaKeyRepository<T> {

    T getFirstByPhone(String phone);

}
