package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;
import ru.project.drivingschool.repository.jpa.JpaEmployeeRepository;
import ru.project.drivingschool.repository.jpa.JpaKeyRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;
import ru.project.drivingschool.service.UserService;
import ru.project.drivingschool.util.ValidationUtil;

@Repository
@Transactional(readOnly = true)
public class UserRepository extends AbstractHistoryRepository<User> {

    private JpaUserRepository repository;

    public UserRepository(JpaUserRepository repository, JpaEmployeeRepository employeeRepository) {
        super(repository, employeeRepository);
        this.repository = repository;
    }

    public User getFirstByPhone(String phone){
        return repository.getFirstByPhone(phone);
    }

}
