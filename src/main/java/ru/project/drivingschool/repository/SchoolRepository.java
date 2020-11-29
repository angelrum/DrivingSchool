package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;
import ru.project.drivingschool.repository.jpa.JpaSchoolRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;
import ru.project.drivingschool.util.ValidationUtil;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SchoolRepository extends AbstractKeyRepository<School> implements InterfaceHistoryRepository<School> {
    
    private JpaSchoolRepository repository;

    private JpaCompanyRepository companyRepository;

    private JpaUserRepository userRepository;

    public SchoolRepository(JpaSchoolRepository repository, JpaCompanyRepository companyRepository, JpaUserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public List<School> getAll(long companyId) {
        return repository.getAll(companyId);
    }
    
    public School getWithUsers(long id) {
        return repository.getWithUsers(id);
    }
    
    public School getWithEmployees(long id) {
        return repository.getWithEmployees(id);
    }

    @Transactional
    public School save(School e, long companyId, long createdBy) {
        Company company = companyId > ValidationUtil.REGISTER_SEQ ? companyRepository.getOne(companyId) : null;
        e.setCompany(company);
        return save(e, createdBy, e.isNew());
    }

    @Override
    public JpaUserRepository getUserRepository() {
        return this.userRepository;
    }
}
