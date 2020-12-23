package ru.project.drivingschool.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.service.CompanyService;
import ru.project.drivingschool.to.CompanyTo;
import ru.project.drivingschool.to.SchoolTo;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController extends AbstractController<Company, CompanyTo> {

    private CompanyService service;

    public CompanyController(CompanyService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(path = "/{companyId}/schools", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SchoolTo> getAllSchools(@PathVariable Long companyId) {
        Company company = service.getWithSchools(companyId);
        return company.getSchools()
                .stream().map(SchoolTo::new)
                .collect(Collectors.toList());
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
}
