package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.repository.jpa.JpaEmployeeRepository;
import ru.project.drivingschool.repository.jpa.JpaKeyRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

@Repository
@Transactional(readOnly = true)
public class UserRepository extends AbstractRepository<User> {

    public UserRepository(JpaUserRepository repository) {
        super(repository);
    }

}
