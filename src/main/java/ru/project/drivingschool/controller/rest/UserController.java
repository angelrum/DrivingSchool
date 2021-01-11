package ru.project.drivingschool.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.service.UserService;
import ru.project.drivingschool.to.SchoolTo;
import ru.project.drivingschool.to.UserTo;

import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
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
        User u = service.get(id);
        return new UserTo(u, u.getSchoolUsers());
    }

    @Override
    UserTo createTo(User user) {
        return new UserTo(user);
    }
}
