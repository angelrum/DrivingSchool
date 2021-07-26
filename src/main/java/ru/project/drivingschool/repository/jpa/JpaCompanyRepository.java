package ru.project.drivingschool.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.directory.Status;

public interface JpaCompanyRepository extends JpaKeyRepository<Company> {

    //https://www.baeldung.com/spring-data-jpa-null-parameters
    @Query("SELECT s FROM Company c LEFT JOIN c.schools s WHERE c.id=:id " +
            "AND (:active IS NULL OR s.active=:active) ORDER BY s.id")
    Page<School> getSchools(@Param("id") Long id, @Param("active") Boolean active, Pageable page);

    @Query("SELECT u.user FROM Company c LEFT JOIN c.companyUsers u WHERE c.id =:id " +
            "AND (:status IS NULL OR u.status=:status) " +
            "AND (:active IS NULL OR u.user.active=:active) " +
            "AND (:role IS NULL OR u.user.id IN ( SELECT ur.user.id FROM UserRoles ur JOIN School s ON s.company.id=:id WHERE ur.role=:role ))")
    Page<User> getUsers(@Param("id") Long id, @Param("active") Boolean active,
                        @Param("status") Status status, @Param("role") Role role,
                        Pageable page);

}
