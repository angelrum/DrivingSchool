package ru.project.drivingschool.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.security.SecurityUtil;
import ru.project.drivingschool.to.UserTo;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserTo login() {
        return SecurityUtil.get().getTo();
    }

}
