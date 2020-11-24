package ru.project.drivingschool.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.AuthorizedUser;
import ru.project.drivingschool.View;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.service.EmployeeService;
import ru.project.drivingschool.to.EmployeeTo;
import java.util.List;

import static ru.project.drivingschool.util.EmployeeUtil.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeTo> getAll() {
        log.info("get all employees for company {}", AuthorizedUser.getCompanyId());
        return createTos(service.getAll(AuthorizedUser.getCompanyId()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeTo get(@PathVariable Long id) {
        log.info("get employees by id {} and company {}", id, AuthorizedUser.getCompanyId());
        return createTo(service.get(AuthorizedUser.getCompanyId(), id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void create(@Validated(View.Web.class)Employee e) {
        log.info("create employee {}", e.toString());
        service.create(e, AuthorizedUser.getCompanyId(), AuthorizedUser.getId());
    }

    @PutMapping
    public void update(@Validated(View.Web.class) Employee e) {
        log.info("update employee {}", e.toString());
        service.update(e, AuthorizedUser.getCompanyId(), AuthorizedUser.getId());
    }

}
