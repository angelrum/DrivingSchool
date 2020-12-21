package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import ru.project.drivingschool.model.User;

import java.util.List;

public interface JpaUserRepository extends JpaKeyRepository<User> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.schoolUsers WHERE u.phone=:phone")
    User getByPhone(String phone);

    List<User> findAllByActiveTrueOrderById();

}
