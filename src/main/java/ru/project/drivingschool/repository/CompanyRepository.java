package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

@Repository
@Transactional(readOnly = true)
public class CompanyRepository extends AbstractKeyHistoryRepository<Company> {

    private JpaCompanyRepository repository;

    public CompanyRepository(JpaCompanyRepository repository, JpaUserRepository userRepository) {
        super(repository, userRepository);
        this.repository = repository;
    }

    public Company getWithSchools(Long id) {
        return repository.getWithSchools(id);
    }
}
