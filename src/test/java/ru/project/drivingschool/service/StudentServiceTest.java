package ru.project.drivingschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.testdata.StudentTestData;

@SpringBootTest
class StudentServiceTest extends AbstractServiceTest<User> {

    @Autowired
    StudentServiceTest(UserService service) {
        super(service, new StudentTestData(), StudentTestData.USER_TEST_MATCHER);
    }
}
