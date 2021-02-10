package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.repository.SchoolRepository;

import java.util.List;

import static ru.project.drivingschool.util.ValidationUtil.*;

@Service
public class SchoolService extends AbstractService<School> {

    private SchoolRepository repository;

    public SchoolService(SchoolRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<School> getAll(long companyId) {
        log.info("get all schools by company. Company id={}", companyId);
        checkByGlobalId(companyId);
        return repository.getAll(companyId);
    }

    public School getWithUsers(long id) {
        log.info("get school with users. Id={}", id);
        checkByGlobalId(id);
        return checkNotFoundWithId(repository.getWithUsers(id), id);
    }

    public School getWithEmployees(long id) {
        log.info("get school with employees. Id={}", id);
        checkByGlobalId(id);
        return checkNotFoundWithId(repository.getWithEmployees(id), id);
    }

    public School create(School school, long companyId, long createdBy) {
        log.info("Create employee {}. Company={} and created={}", school.toString(), companyId, createdBy);
        checkNew(school);
        return this.save(school, companyId, createdBy);
    }

    public School update(School school, long companyId, long changedBy) {
        log.info("Update employee {}. Company={} and changed={}", school.toString(), companyId, changedBy);
        return this.save(school, companyId, changedBy);
    }

    private School save(School school, long companyId, long changedBy) {
        checkNotNull(school);
        return checkNotFoundWithId(repository.save(school, companyId, changedBy), companyId, changedBy);
    }
}
