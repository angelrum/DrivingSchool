package ru.project.drivingschool.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.security.SecurityUtil;
import ru.project.drivingschool.to.UserTo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserTo login() {
        return SecurityUtil.get().getTo();
    }

//    @RequestMapping(path = "/not-login")
//    public ErrorInfo notLogin() {
//        return new ErrorInfo("", ErrorType.AUTHORIZARION_FAILED, ErrorType.AUTHORIZARION_FAILED.getErrorCode());
//    }

    @GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserTo getAuthUser() {
        return SecurityUtil.get().getTo();
    }
}
