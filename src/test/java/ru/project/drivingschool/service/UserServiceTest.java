package ru.project.drivingschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.testdata.UserTestData;
import static ru.project.drivingschool.testdata.UserTestData.*;

@SpringBootTest
class UserServiceTest extends AbstractServiceTest<User> {

    @Autowired
    UserServiceTest(UserService service) {
        super(service, new UserTestData(), USER_TEST_MATCHER);
    }
}
