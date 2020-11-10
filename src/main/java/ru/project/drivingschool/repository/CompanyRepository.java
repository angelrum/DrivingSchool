package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;

@Repository
@Transactional(readOnly = true)
public class CompanyRepository extends AbstractRepository<Company> {

    public CompanyRepository(JpaCompanyRepository repository) {
        super(repository);
    }
}
