package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.repository.jpa.JpaSchoolRepository;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SchoolRepository extends AbstractRepository<School> {
    
    private JpaSchoolRepository repository;

    public SchoolRepository(JpaSchoolRepository repository) {
        super(repository);
        this.repository = repository;
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
}
