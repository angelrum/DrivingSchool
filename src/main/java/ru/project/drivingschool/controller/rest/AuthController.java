package ru.project.drivingschool.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.security.SecurityUtil;
import ru.project.drivingschool.service.CompanyService;
import ru.project.drivingschool.service.UserService;
import ru.project.drivingschool.to.UserTo;

import java.util.function.Consumer;

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
        return new UserTo(SecurityUtil.get().getUser());
    }

    @GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserTo getAuthUser() {
        User authUser = service.get(SecurityUtil.authUserId());
        return new UserTo(authUser, authUser.getSchoolUsers());
    }
}
