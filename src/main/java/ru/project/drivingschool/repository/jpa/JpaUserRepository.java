package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.drivingschool.model.User;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaKeyRepository<User> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.schoolUsers WHERE u.phone=:phone")
    User getByPhone(String phone);

    @Override
    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.schoolUsers su " +
            "LEFT JOIN FETCH su.school s LEFT JOIN FETCH s.company " +
            "WHERE u.id=:id")
    Optional<User> findById(@Param("id") Long id);

    List<User> findAllByActiveTrueOrderById();

}
