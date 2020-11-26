package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.repository.UserRepository;

import static ru.project.drivingschool.util.ValidationUtil.*;

@Service
public class UserService extends AbstractService<User>{
    
    private UserRepository repository;
    
    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }
    
    public User getFirstByPhone(String phone){
        checkNotNull(phone);
        return checkNotFound(repository.getFirstByPhone(phone), String.format("Not found with phone=%s", phone));
    }

    public User create(User user, long createdBy) {
        log.info("Create user {}. Created={}", user.toString(), createdBy);
        checkNew(user);
        return this.save(user, createdBy);
    }

    public User update(User user, long changedBy) {
        log.info("Update user {}. Changed={}", user.toString(), changedBy);
        return this.save(user, changedBy);
    }

    private User save(User user, long createdBy) {
        checkNotNull(user);
        return repository.save(user, createdBy);
    }
}
