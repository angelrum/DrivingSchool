package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.repository.CompanyRepository;

@Service
public class CompanyService extends AbstractService<Company> {

    public CompanyService(CompanyRepository repository) {
        super(repository);
    }
}
