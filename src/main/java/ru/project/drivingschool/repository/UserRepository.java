package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

@Repository
@Transactional(readOnly = true)
public class UserRepository extends AbstractKeyRepository<User> implements InterfaceHistoryRepository<User> {

    private JpaUserRepository repository;

    public UserRepository(JpaUserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public User getFirstByPhone(String phone){
        return repository.getFirstByPhone(phone);
    }

    @Override
    public JpaUserRepository getUserRepository() {
        return this.repository;
    }
}
