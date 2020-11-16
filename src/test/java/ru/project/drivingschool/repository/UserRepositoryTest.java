package ru.project.drivingschool.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    protected UserRepository repository;

    @Test
    void getAll() {
        repository.getAll().forEach(System.out::println);
    }

    @Test
    void get(){
       repository.get((long) 1002);
    }

    @Test
    void createNew(){

    }

}
