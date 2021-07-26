package ru.project.drivingschool.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.embedded.SchoolUserId;
import ru.project.drivingschool.model.link.UserRoles;
import ru.project.drivingschool.service.UserService;
import ru.project.drivingschool.to.ResultPage;
import ru.project.drivingschool.to.UserTo;
import ru.project.drivingschool.util.Util;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User, UserTo> {

    private final UserService service;

    public UserController(UserService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResultPage<UserTo> get(@RequestParam(required = false) Long id,
                                                @RequestParam(required = false) Boolean active,
                                                @RequestParam(required = false) Integer limit,
                                                @RequestParam(required = false) Integer offset) {
        return super.get(id, active, limit, offset);
    }

    @GetMapping(path = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResultPage<UserTo> get(@RequestParam(required = false) String phone,
                                                @RequestParam(required = false) @Valid @Email String email,
                                                @RequestParam(required = false) String lastname,
                                                @RequestParam(required = false) String firstname,
                                                @RequestParam(required = false) Integer limit,
                                                @RequestParam(required = false) Integer offset ) {
        ResultPage<User> result;
        boolean limitParam = Objects.isNull(limit) || Objects.isNull(offset);
        if (!StringUtils.hasText(phone) && !StringUtils.hasText(email)
                && !StringUtils.hasText(lastname) && !StringUtils.hasText(firstname)) {
            result = limitParam ? this.service.getAll() : this.service.getAll(offset, limit);
        } else {
            result = limitParam ? this.service.getAll(phone, email, lastname, firstname)
                    : this.service.getAll(offset, limit, phone, email, lastname, firstname);
        }
        return fromTtoTo(result);
    }

    @Override
    UserTo createTo(User user) {
        return new UserTo(user);
    }

    @Override
    User convertTOinT(UserTo to) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        User u = Util.copyFieldToFieldByName(to, User.class);
        Set<UserRoles> userRoles = new HashSet<>();
        to.getRoles()
                .forEach(urTo -> urTo.getType()
                        .forEach(type -> userRoles.add(new UserRoles(new SchoolUserId(urTo.getSchoolId(), null), type))));
        u.setRoles(userRoles);
        return u;
    }
}
