package ru.project.drivingschool.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.testdata.SchoolTestData;
import ru.project.drivingschool.testdata.UserTestData;

import java.util.List;

import static ru.project.drivingschool.testdata.SchoolTestData.*;

@SpringBootTest
public class SchoolServiceTest extends AbstractServiceTest<School> {

    private UserTestData userTestData = new UserTestData();

    private SchoolTestData testData = new SchoolTestData();

    private SchoolService service;

    @Autowired
    public SchoolServiceTest(SchoolService service) {
        super(service, new SchoolTestData(), SCHOOL_MATCHER);
        this.service = service;
    }

    @Test
    void getAllUsers() {
        System.out.println(service.getWithUsers(testData.getId1()).getUsers().size());
        List<User> users = service.getWithUsers(testData.getId2()).getUsers();
        users.add(new User(1003L, "8(911)111-11-11", "12345",
                null, "Иван", "Иванов", "Иванович", "ivan@ivan.ru",
                true, null, null,
                null, null, null));
        System.out.println(users);
    }

    void getAllEmployees() {

    }

    void getAllByCompany() {

    }
}
