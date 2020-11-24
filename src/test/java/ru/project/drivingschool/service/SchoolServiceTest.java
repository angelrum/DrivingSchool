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
        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(users),
                List.of(userData.getObjectById(userData.getId1())));
    }

    @Test
    void getAllByCompany() {
        SCHOOL_MATCHER.assertMatch(service.getAll(companyData.getId1()), testData.getAll());
    }

    @Test
    void addUserInSchool() {
        School school = service.getWithUsers(testData.getId1());
        //проверяем, что связь есть только по одному ученику
        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()),
                List.of(userData.getObjectById(userData.getId1())));
        SchoolUsers su = createSchoolUser(school, userData.getId2());
        school.setUsers(Set.of(su));
        service.update(school);
        school = service.getWithUsers(testData.getId1());
        //проверяем, что связь появилась по двум ученикам
        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()), userData.getAll());
    }

    @Test
    void updateUserSchool() {
        School school = service.getWithUsers(testData.getId1());
        //проверяем, что связь есть только по одному ученику
        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()),
                List.of(userData.getObjectById(userData.getId1())));
        SchoolUsers su = school.getUsers().iterator().next();
        //проверяем, что связь с параметром enable = true
        Assertions.assertThat(su.getEnable()).as("Enable должно быть равно true").isTrue();
        su.setEnable(false);
        school.setUsers(Set.of(su));
        service.update(school);
        school = service.getWithUsers(testData.getId1());
        //проверяем, что связь есть только по одному ученику
        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()),
                List.of(userData.getObjectById(userData.getId1())));
        su = school.getUsers().iterator().next();
        //проверяем, что связь с параметром enable = false
        Assertions.assertThat(su.getEnable()).as("Enable должно быть равно false").isFalse();
    }

    @Test
    void updateSchoolsWithoutSchoolUsers() {
        School school = service.getWithUsers(testData.getId1());
        //проверяем, что связь есть только по одному ученику
        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()),
                List.of(userData.getObjectById(userData.getId1())));
        School update = testData.getUpdate();
        service.update(update);
        school = service.getWithUsers(testData.getId1());
        SCHOOL_MATCHER.assertMatch(school, update);
        //проверяем, что связь есть только по одному ученику
        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()),
                List.of(userData.getObjectById(userData.getId1())));
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
        //добавили еще двух преподавателей
        school.getEmployees().addAll(List.of(se1, se2));
        service.update(school);
        school = service.getWithEmployees(testData.getId1());
        List<Employee> employees = employeeData.getEmployeeFromSchoolEmployees(school.getEmployees());
        employees.sort(Comparator.comparing(Employee::id));
        EMPLOYEE_MATCHER.assertMatch(employees, employeeData.getAll());
    }

    @Test
    void updateEmployeeConnect() {
        School school = service.getWithEmployees(testData.getId1());
        SchoolEmployees se = school.getEmployees().iterator().next();
        Assertions.assertThat(se.getEnable()).as("Enable должно быть равно true").isTrue();
        se.setEnable(false);
        school.setEmployees(Set.of(se));
        service.update(school);
        school = service.getWithEmployees(testData.getId1());
        se = school.getEmployees().iterator().next();
        Assertions.assertThat(se.getEnable()).as("Enable должно быть равно false").isFalse();
    }

    @Test
    void updateSchoolWithoutEmployees() {
        School school = service.getWithEmployees(testData.getId1());
        List<Employee> employees = employeeData.getEmployeeFromSchoolEmployees(school.getEmployees());
        EMPLOYEE_MATCHER.assertMatch(employees, List.of(employeeData.getObjectById(employeeData.getId1())));
        school = testData.getUpdate();
        service.update(school);
        school = service.getWithEmployees(testData.getId1());
        employees = employeeData.getEmployeeFromSchoolEmployees(school.getEmployees());
        EMPLOYEE_MATCHER.assertMatch(employees, List.of(employeeData.getObjectById(employeeData.getId1())));
    }

    private SchoolUsers createSchoolUser(School school, Long userId) {
        return new SchoolUsers(school, userData.getObjectById(userId));
    }

    private SchoolEmployees createSchoolEmployee(School school, Long employeeId) {
        return new SchoolEmployees(school, employeeData.getObjectById(employeeId));
    }
}
