package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.repository.jpa.JpaEmployeeRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

@Repository
public class UserRepository extends AbstractNamedHistoryRepository<User> {

    public UserRepository(JpaUserRepository repository, JpaEmployeeRepository employeeRepository) {
        super(repository, employeeRepository);
    }
}
