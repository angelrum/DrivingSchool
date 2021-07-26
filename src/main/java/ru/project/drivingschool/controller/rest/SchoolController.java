package ru.project.drivingschool.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.link.SchoolUsers;
import ru.project.drivingschool.security.SecurityUtil;
import ru.project.drivingschool.service.SchoolService;
import ru.project.drivingschool.to.ResultPage;
import ru.project.drivingschool.to.SchoolTo;
import ru.project.drivingschool.to.UserTo;
import ru.project.drivingschool.util.Util;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/schools")
public class SchoolController extends AbstractController<School, SchoolTo> {

    private final SchoolService service;

    public SchoolController(SchoolService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResultPage<SchoolTo> get(@RequestParam(required = false) Long id,
                                                  @RequestParam(required = false) Boolean active,
                                                  @RequestParam(required = false) Integer limit,
                                                  @RequestParam(required = false) Integer offset) {
        return super.get(id, active, limit, offset);
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
    SchoolTo createTo(School s) {
        return new SchoolTo(s);
    }

    @Override
    School convertTOinT(SchoolTo to) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return Util.copyFieldToFieldByName(to, School.class);
    }
}
