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

    @Autowired
    UserServiceTest(UserService service) {
        super(service, new UserTestData(), UserTestData.USER_TEST_MATCHER);
    }


}
