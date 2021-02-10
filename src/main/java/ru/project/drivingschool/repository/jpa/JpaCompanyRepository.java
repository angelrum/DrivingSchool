package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.drivingschool.model.Company;

public interface JpaCompanyRepository extends JpaKeyRepository<Company> {

    @Query("SELECT c FROM Company c LEFT JOIN FETCH c.schools WHERE c.id =:id")
    Company getWithSchools(@Param("id") Long id);
}
