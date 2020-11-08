package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.drivingschool.model.Company;

public interface JpaCompanyRepository extends JpaRepository<Company, Long> {

}
