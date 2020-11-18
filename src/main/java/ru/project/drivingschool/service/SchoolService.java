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

    public School getWithEmployee(long id) {
        log.info("get school with employees. Id={}", id);
        checkByGlobalId(id);
        return checkNotFoundWithId(repository.getWithEmployee(id), id);
    }
}
