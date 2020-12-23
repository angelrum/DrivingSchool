package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.repository.CompanyRepository;

import java.util.List;
import java.util.Set;

@Service
public class CompanyService extends AbstractService<Company> {

    private CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Company getWithSchools(Long id) {
        return repository.getWithSchools(id);
    }

//    public List<Company> getAll() {
//        log.info("Get all companies");
//        return repository.getAll();
//    }
}
