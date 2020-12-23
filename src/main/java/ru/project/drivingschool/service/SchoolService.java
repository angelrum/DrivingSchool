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

//    public List<School> getAll() {
//        log.info("get all schools");
//        return repository.getAll();
//    }

    public List<School> getAll(long companyId) {
        log.info("get all schools by company with id={}", companyId);
        checkByGlobalId(companyId);
        return repository.getAll(companyId);
    }

    public List<School> getAllByCity(String city) {
        log.info("get all school by city {}", city);
        return repository.getByCity(city);
    }


    public School getWithUsers(long id) {
        log.info("get school with all users. Id = {}", id);
        checkByGlobalId(id);
        return checkNotFoundWithId(repository.getWithUsers(id), id);
    }

    @Override
    public School create(School school, Long createdBy) {
        log.info("Create schools {}. Created user = {}", school.toString(), createdBy);
        checkNew(school);
        return this.save(school, createdBy);
    }

    @Override
    public School update(School school, Long changedBy) {
        log.info("Update school {}. Changed user = {}", school.toString(), changedBy);
        return this.save(school, changedBy);
    }

    private School save(School school, long changedBy) {
        checkNotNull(school);
        return checkNotFoundWithId(repository.save(school, changedBy), changedBy);
    }
}
