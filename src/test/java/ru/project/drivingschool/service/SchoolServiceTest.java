package ru.project.drivingschool.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.testdata.CompanyTestData;
import ru.project.drivingschool.testdata.EmployeeTestData;
import ru.project.drivingschool.testdata.SchoolTestData;
import ru.project.drivingschool.testdata.UserTestData;

import java.util.List;

import static ru.project.drivingschool.testdata.SchoolTestData.*;
import static ru.project.drivingschool.testdata.UserTestData.*;

@SpringBootTest
class SchoolServiceTest extends AbstractServiceTest<School> {

    private UserTestData userData = new UserTestData();

    private EmployeeTestData employeeData = new EmployeeTestData();

    private CompanyTestData companyData = new CompanyTestData();

    private SchoolTestData testData = new SchoolTestData();

    private SchoolService service;

    private UserService userService;

    @Autowired
    SchoolServiceTest(SchoolService service, UserService userService) {
        super(service, new SchoolTestData(), SCHOOL_MATCHER);
        this.service = service;
        this.userService = userService;
    }

    @Test
    void getAllUsers() {
        List<User> users = service.getWithUsers(testData.getId1()).getUsers();
        USER_TEST_MATCHER.assertMatch(users, userData.getAll());
    }

    @Test
    void getAllEmployees() {
        EmployeeTestData.EMPLOYEE_MATCHER.assertMatch(
                service.getWithEmployee(testData.getId1()).getEmployees(),
                List.of(employeeData.getObjectById(employeeData.getId1())));
    }

    @Test
    void getAllByCompany() {
        SCHOOL_MATCHER.assertMatch(service.getAll(companyData.getId1()), testData.getAll());
    }

    @Test
    void setNewUser() {
        School school = service.getWithUsers(testData.getId2());
        school.getUsers().add(userService.get(userData.getId1()));
        service.update(school);
        school = service.getWithUsers(testData.getId2());
        school.getUsers().add(userService.get(userData.getId2()));
        service.update(school);
    }
}
