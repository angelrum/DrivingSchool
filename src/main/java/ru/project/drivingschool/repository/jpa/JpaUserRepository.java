package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.drivingschool.model.User;

import java.util.List;

public interface JpaUserRepository extends JpaKeyRepository<User> {

    User getFirstByPhone(String phone);

    List<User> getAllByCompanyIdOrderById(long companyId);

    User findFirstByCompanyIdAndId(long companyId, long id);

    @Modifying
    @Query("DELETE FROM User u WHERE u.company.id =:companyId AND u.id =:id")
    int delete(@Param("companyId") long companyId, @Param("id") long id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.schoolStudents WHERE u.id=:id")
    User getWithSchoolAsStudent(@Param("id") long id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.schoolEmployees WHERE u.id=:id")
    User getWithSchoolAsEmployee(@Param("id") long id);
}
