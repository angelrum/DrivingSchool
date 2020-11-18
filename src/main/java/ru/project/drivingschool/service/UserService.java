package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.repository.UserRepository;

import static ru.project.drivingschool.util.ValidationUtil.checkNotFound;
import static ru.project.drivingschool.util.ValidationUtil.checkNotNull;

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
}
