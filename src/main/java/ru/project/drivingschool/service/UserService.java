package ru.project.drivingschool.service;

import ru.project.drivingschool.model.User;
import ru.project.drivingschool.repository.AbstractRepository;

public class UserService extends AbstractService<User>{
    public UserService(AbstractRepository<User> repository) {
        super(repository);
    }
}
