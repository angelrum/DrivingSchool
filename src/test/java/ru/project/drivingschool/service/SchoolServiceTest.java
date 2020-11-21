package ru.project.drivingschool.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.embedded.SchoolEmployees;
import ru.project.drivingschool.model.embedded.SchoolUsers;
import ru.project.drivingschool.testdata.CompanyTestData;
import ru.project.drivingschool.testdata.EmployeeTestData;
import ru.project.drivingschool.testdata.SchoolTestData;
import ru.project.drivingschool.testdata.UserTestData;

import java.util.*;

import static ru.project.drivingschool.testdata.EmployeeTestData.EMPLOYEE_MATCHER;
import static ru.project.drivingschool.testdata.SchoolTestData.*;
import static ru.project.drivingschool.testdata.UserTestData.USER_TEST_MATCHER;

@SpringBootTest
class SchoolServiceTest extends AbstractServiceTest<School> {

    private UserTestData userData = new UserTestData();

    private EmployeeTestData employeeData = new EmployeeTestData();

    private CompanyTestData companyData = new CompanyTestData();

    private SchoolTestData testData = new SchoolTestData();

    private SchoolService service;

    @Autowired
    SchoolServiceTest(SchoolService service, UserService userService) {
        super(service, new SchoolTestData(), SCHOOL_MATCHER);
        this.service = service;
    }

    @Test
    void getAllUsers() {
        Set<SchoolUsers> users = service.getWithUsers(testData.getId1()).getUsers();
        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(users), userData.getAll());
    }

    @Test
    void getAllByCompany() {
        SCHOOL_MATCHER.assertMatch(service.getAll(companyData.getId1()), testData.getAll());
    }

    @Test
    void addUserInSchool() {
        School school = service.getWithUsers(testData.getId2());
        SchoolUsers su1 = createSchoolUser(school, userData.getId1());
        SchoolUsers su2 = createSchoolUser(school, userData.getId2());
        school.getUsers().addAll(List.of(su1, su2));
        service.update(school);
        school = service.getWithUsers(testData.getId2());
        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()), userData.getAll());
    }

    @Test
    void removeUserFromSchool() {
        School school = service.getWithUsers(testData.getId1());
        school.getUsers().removeIf(su->su.getUser().id() == userData.getId1());
        service.update(school);
        school = service.getWithUsers(testData.getId1());
        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()), List.of(userData.getObjectById(userData.getId2())));
    }

    @Test
    void deleteAllUsers() {
        School school = service.getWithUsers(testData.getId1());
        school.setUsers(new HashSet<>());
        service.update(school);
        school = service.getWithUsers(testData.getId1());
        Assertions.assertThat(school.getUsers()).hasSize(0);
    }

    @Test
    void getAllEmployees() {
        Set<SchoolEmployees> employees = service.getWithEmployees(testData.getId1()).getEmployees();
        Employee emp = employeeData.getEmployeeFromSchoolEmployees(employees).get(0);
        EMPLOYEE_MATCHER.assertMatch(emp, employeeData.getObjectById(employeeData.getId1()));
    }

    @Test
    void addEmployeesToSchool() {
        School school = service.getWithEmployees(testData.getId1());
        SchoolEmployees se1 = createSchoolEmployee(school, employeeData.getId2());
        SchoolEmployees se2 = createSchoolEmployee(school, employeeData.getId3());
        school.getEmployees().addAll(List.of(se1, se2));
        service.update(school);
        school = service.getWithEmployees(testData.getId1());
        List<Employee> employees = employeeData.getEmployeeFromSchoolEmployees(school.getEmployees());
        employees.sort(Comparator.comparing(Employee::id));
        EMPLOYEE_MATCHER.assertMatch(employees, employeeData.getAll());
    }

    @Test
    void removeAllEmployees() {
        School school = service.getWithEmployees(testData.getId1());
        school.setEmployees(null);
        school = service.getWithEmployees(testData.getId1());
        Assertions.assertThat(school.getEmployees()).hasSize(0);
    }

    private SchoolUsers createSchoolUser(School school, Long userId) {
        return new SchoolUsers(school, userData.getObjectById(userId));
    }

    private SchoolEmployees createSchoolEmployee(School school, Long employeeId) {
        return new SchoolEmployees(school, employeeData.getObjectById(employeeId));
    }
}
