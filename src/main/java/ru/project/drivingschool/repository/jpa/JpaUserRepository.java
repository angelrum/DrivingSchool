package ru.project.drivingschool.repository.jpa;

import ru.project.drivingschool.model.User;

public interface JpaUserRepository extends JpaNamedRepository<User> {
    User getAllByFirstnameAndEmail(String firstname, String email);
}
