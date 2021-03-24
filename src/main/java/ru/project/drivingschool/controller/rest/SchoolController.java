package ru.project.drivingschool.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.embedded.SchoolUsers;
import ru.project.drivingschool.security.SecurityUtil;
import ru.project.drivingschool.service.SchoolService;
import ru.project.drivingschool.to.SchoolTo;
import ru.project.drivingschool.to.UserTo;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/schools")
public class SchoolController extends AbstractController<School, SchoolTo> {

    private SchoolService service;

    public SchoolController(SchoolService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(path = "/{id}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<UserTo> getAllUsers(@PathVariable Long id) {
        School school = this.service.getWithUsers(id);
        return school.getSchoolUsers()
                .stream()
                .map(SchoolUsers::getUser)
                .map(UserTo::new)
                .collect(Collectors.toSet());
    }

    @Override
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody School school) {
        log.info("update employee {}", school.toString());
        service.update(school, SecurityUtil.authUserId());
    }

    @Override
    SchoolTo createTo(School s) {
        return new SchoolTo(s);
    }
}
