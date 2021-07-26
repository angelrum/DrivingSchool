package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.drivingschool.model.School;

import java.util.List;

public interface JpaSchoolRepository extends JpaKeyRepository<School> {

    @Query("SELECT s FROM School s LEFT JOIN FETCH s.schoolUsers WHERE s.id=:id")
    School getWithUsers(@Param("id") long id);
    
//    @Query("SELECT s FROM School s WHERE s.company.id=:companyId")
//    List<School> getAll(@Param("companyId") long companyId);

    @Query("SELECT s FROM School s WHERE s.address.city LIKE %:city% AND s.active = true")
    List<School> getByCity(@Param("city") String city);

}
