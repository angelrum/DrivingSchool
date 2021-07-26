package ru.project.drivingschool.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.directory.Status;
import ru.project.drivingschool.service.CompanyService;
import ru.project.drivingschool.to.CompanyTo;
import ru.project.drivingschool.to.ResultPage;
import ru.project.drivingschool.to.SchoolTo;
import ru.project.drivingschool.to.UserTo;
import ru.project.drivingschool.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController extends AbstractController<Company, CompanyTo> {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResultPage<CompanyTo> get(@RequestParam(required = false) Long id,
                                                   @RequestParam(required = false) Boolean active,
                                                   @RequestParam(required = false) Integer limit,
                                                   @RequestParam(required = false) Integer offset) {
        return super.get(id, active, limit, offset);
    }

    @GetMapping(path = "/{companyId}/schools", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResultPage<SchoolTo> getSchools(@PathVariable Long companyId,
                                                         @RequestParam(required = false) Boolean active,
                                                         @RequestParam(required = false) Integer limit,
                                                         @RequestParam(required = false) Integer offset) {
        ResultPage<School> result;
        if (Objects.nonNull(limit) && Objects.nonNull(offset))
            result = service.getSchools(companyId, offset, limit, active);
        else
            result = service.getSchools(companyId, active);
        List<SchoolTo> schools = result.getResult().stream().map(SchoolTo::new).collect(Collectors.toList());
        return new ResultPage<> (result.getIndex(), schools);
    }

    @GetMapping(path = "/{companyId}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResultPage<UserTo> getUsers(@PathVariable Long companyId,
                                                     @RequestParam(required = false) String type,
                                                     @RequestParam(required = false) String status,
                                                     @RequestParam(required = false) Boolean active,
                                                     @RequestParam(required = false) Integer limit,
                                                     @RequestParam(required = false) Integer offset) {
        Role role = Objects.nonNull(type) ? Util.getEnumFromString(Role.class, type) : null;
        Status st = Objects.nonNull(status) ? Util.getEnumFromString(Status.class, status) : null;
        ResultPage<User> result;
        if (Objects.nonNull(limit) && Objects.nonNull(offset))
            result = service.getUsers(companyId, offset, limit, active, st, role);
        else
            result = service.getUsers(companyId, active, st, role);
        List<UserTo> users = result.getResult().stream().map(UserTo::new).collect(Collectors.toList());
        return new ResultPage<>(result.getIndex(), users);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Override
    CompanyTo createTo(Company c) {
        return new CompanyTo(c);
    }

    @Override
    Company convertTOinT(CompanyTo to) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return Util.copyFieldToFieldByName(to, Company.class);
    }
}
