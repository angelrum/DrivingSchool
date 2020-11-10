package ru.project.drivingschool.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyRepositoryTest {

    @Autowired
    protected CompanyRepository repository;

    @Test
    void getAll() {
        repository.getAll().forEach(System.out::println);
    }

}