package ru.project.drivingschool.repository;

import ru.project.drivingschool.model.School;
import ru.project.drivingschool.repository.jpa.JpaKeyRepository;
import ru.project.drivingschool.repository.jpa.JpaSchoolRepository;

public class SchoolRepository extends AbstractRepository<School> {

    public SchoolRepository(JpaSchoolRepository repository) {
        super(repository);
    }
}
