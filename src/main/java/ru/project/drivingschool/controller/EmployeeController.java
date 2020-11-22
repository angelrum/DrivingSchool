package ru.project.drivingschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.drivingschool.AuthorizedUser;
import ru.project.drivingschool.service.EmployeeService;
import ru.project.drivingschool.to.EmployeeTo;
import java.util.List;

import static ru.project.drivingschool.util.EmployeeUtil.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

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
}
