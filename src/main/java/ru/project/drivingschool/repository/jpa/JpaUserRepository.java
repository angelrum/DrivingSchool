package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.drivingschool.model.User;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaKeyRepository<User> {
//            "LEFT JOIN FETCH u.schoolUsers su " +
    @Query("SELECT u FROM User u " +
            "WHERE u.phone=:phone")
    User getByPhone(String phone);

    //            "LEFT JOIN FETCH u.schoolUsers su " +
    //            "LEFT JOIN FETCH su.school s LEFT JOIN FETCH s.company " +
    @Override
    @Query("SELECT u FROM User u " +
            "WHERE u.id=:id")
    Optional<User> findById(@Param("id") Long id);

    List<User> findAllByActiveTrueOrderById();

}
