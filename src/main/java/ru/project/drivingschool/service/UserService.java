package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.repository.UserRepository;

@Service
public class UserService extends AbstractService<User>{
    public UserService(UserRepository repository) {
        super(repository);
    }
}
