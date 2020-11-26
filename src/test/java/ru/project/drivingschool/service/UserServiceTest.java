package ru.project.drivingschool.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.TimingExtension;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.testdata.EmployeeTestData;
import ru.project.drivingschool.testdata.TestDataInterface;
import ru.project.drivingschool.testdata.UserTestData;
import ru.project.drivingschool.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static ru.project.drivingschool.testdata.UserTestData.*;

@SpringBootTest
//@ExtendWith(TimingExtension.class)
//@Sql(scripts = "classpath:db/data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest extends AbstractServiceTest<User>{

    private UserTestData userData = new UserTestData();

    private EmployeeTestData employeeData = new EmployeeTestData();

    private UserService service;

    @Autowired
    UserServiceTest(UserService service) {
        super(service, new UserTestData(), UserTestData.USER_TEST_MATCHER);
        this.service = service;
    }

    @Test
    @Override
    void create() {
        User usNew = userData.getNew();
        User create = service.create(usNew, employeeData.getId1());
        usNew.setId(create.getId());
        List<User> list = new ArrayList<>(userData.getAll());
        list.add(usNew);
        USER_TEST_MATCHER.assertMatch(service.get(usNew.id()), usNew);
        USER_TEST_MATCHER.assertMatch(service.getAll(), list);
    }

    @Test
    @Override
    void update() {
        User update = userData.getUpdate();
        service.update(update, employeeData.getId1());
        USER_TEST_MATCHER.assertMatch(service.get(update.id()), update);

    }
}
