package ru.project.drivingschool.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.service.AbstractService;
import ru.project.drivingschool.service.UserService;
import ru.project.drivingschool.to.UserTo;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User, UserTo> {

    private UserService service;

    public UserController(UserService service) {
        super(service);
        this.service = service;
    }

    @Override
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserTo get(@PathVariable Long id) {
        log.info("get users by id {} with scholls", id);

        return super.get(id);
    }

    @Override
    UserTo createTo(User user) {
        return new UserTo(user);
    }
}
