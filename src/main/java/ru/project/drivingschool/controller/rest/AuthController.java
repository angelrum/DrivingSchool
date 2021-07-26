package ru.project.drivingschool.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.security.SecurityUtil;
import ru.project.drivingschool.service.UserService;
import ru.project.drivingschool.to.UserTo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserTo login() {
        return new UserTo();
    }

    @GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserTo getAuthUser() {
        User authUser = service.get(SecurityUtil.getInstance().authUserId());
        return null; //new UserTo(authUser, authUser.getSchoolUsers());
    }
}
