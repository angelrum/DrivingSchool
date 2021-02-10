package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;

@Repository
@Transactional(readOnly = true)
public class CompanyRepository extends AbstractRepository<Company> {

    private JpaCompanyRepository repository;

    public CompanyRepository(JpaCompanyRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Company getWithSchools(Long id) {
        return repository.getWithSchools(id);
    }
}
