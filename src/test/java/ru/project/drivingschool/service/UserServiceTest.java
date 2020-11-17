package ru.project.drivingschool.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.project.drivingschool.TimingExtension;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.testdata.UserTestData;
import ru.project.drivingschool.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static ru.project.drivingschool.testdata.UserTestData.*;

@SpringBootTest
@ExtendWith(TimingExtension.class)
@Sql(scripts = "classpath:db/data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    /*
    @Autowired
    protected UserService service;

    @Test
    void getAll() {
        USER_TEST_MATCHER.assertMatch(service.getAll(), Users);
    }

    @Test
    void get() {
        User user = service.get(USER_ID1);
        USER_TEST_MATCHER.assertMatch(User1, user);
    }

    @Test
    void create() {
        User user = getNewUser();
        service.create(user);
        List<User> list = new ArrayList<>(Users);
        list.add(user);
        USER_TEST_MATCHER.assertMatch(service.getAll(), list);
    }

    @Test
    void update() {
        User user = getUpdateUser();
        User upd = service.update(user);
        USER_TEST_MATCHER.assertMatch(upd, user);
    }

    @Test
    void delete() {
        USER_TEST_MATCHER.assertMatch(service.getAll(), Users);
        service.delete(USER_ID1);
        USER_TEST_MATCHER.assertMatch(service.getAll(), List.of(User2));
    }

    @Test
    void deleteNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND_ID));
    }

    @Test
    void updateNotFoundException() {
        User upd = getUpdateUser();
        upd.setId(NOT_FOUND_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.update(upd));
    }


     */
}
