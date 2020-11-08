package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.repository.CompanyRepository;
import ru.project.drivingschool.util.ValidationUtil;

import java.util.List;
import static ru.project.drivingschool.util.ValidationUtil.*;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> getAll() {
        return repository.getAll();
    }

    public Company get(long id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    public Company save(Company company) {
        checkNotNull(company);
        checkNew(company);
        return repository.save(company);
    }

    public Company update(Company company) {
        checkNotNull(company);
        return checkNotFoundWithId(repository.save(company), company.id());
    }
}
