package ru.project.drivingschool.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.service.CompanyService;
import ru.project.drivingschool.to.CompanyTo;

@RestController
@RequestMapping("/companies")
public class CompanyController extends AbstractController<Company, CompanyTo> {

    public CompanyController(CompanyService service) {
        super(service);
    }

    @Override
    CompanyTo createTo(Company company) {
        return new CompanyTo(company);
    }
}
