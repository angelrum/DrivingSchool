package ru.project.drivingschool.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest {
    @Autowired
    protected UserRepository repository;

    @Test
    void getAll() {
        repository.getAll().forEach(System.out::println);
    }
}
