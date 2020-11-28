package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;

public interface JpaUserRepository extends JpaNamedRepository<User> {
    User getAllByFirstnameAndEmail(String firstname, String email);

//    @Query("SELECT s FROM School s LEFT JOIN FETCH s.users WHERE s.id=:id")
//    School getWithUsers(@Param("id") long id);
}
