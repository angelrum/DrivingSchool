package ru.project.drivingschool.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.User;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    protected UserRepository repository;

    @Test
    void getAll() {
//        for (User user : repository.getAll().toList()) {
//            System.out.println(user);
//            user.getRoles().forEach(System.out::println);
//        }
    }

    @Test
    void get(){
       repository.get((long) 1002);
    }

    @Test
    void createNew(){

    }

}
