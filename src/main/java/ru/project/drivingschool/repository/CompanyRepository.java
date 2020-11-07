package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;


@Repository
public class CompanyRepository extends AbstractRepository<Company, Integer> {

    public CompanyRepository(JpaCompanyRepository repository) {
        super(repository);
    }
}
