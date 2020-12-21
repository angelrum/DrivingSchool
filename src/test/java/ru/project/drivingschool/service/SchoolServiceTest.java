package ru.project.drivingschool.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.testdata.CompanyTestData;
import ru.project.drivingschool.testdata.SchoolTestData;
import ru.project.drivingschool.testdata.UserTestData;

import java.util.*;

import static ru.project.drivingschool.testdata.SchoolTestData.*;

@SpringBootTest
class SchoolServiceTest extends AbstractServiceTest<School> {

    private UserTestData userData = new UserTestData();

    private CompanyTestData companyData = new CompanyTestData();

    private SchoolTestData testData = new SchoolTestData();

    private SchoolService service;

    @Autowired
    SchoolServiceTest(SchoolService service) {
        super(service, new SchoolTestData(), SCHOOL_MATCHER);
        this.service = service;
    }

//    @Test
//    @Override
//    void create() {
//        School schNew = testData.getNew();
//        School create = service.create(schNew, schNew.getCompany().id(), employeeData.getId1());
//        schNew.setId(create.getId());
//        List<School> list = new ArrayList<>(testData.getAll());
//        list.add(schNew);
//        SCHOOL_MATCHER.assertMatch(service.get(schNew.id()), schNew);
//        SCHOOL_MATCHER.assertMatch(service.getAll(), list);
//    }

//    @Test
//    void getAllUsers() {
//        Set<SchoolUsers> users = service.getWithUsers(testData.getId1()).getUsers();
//        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(users),
//                List.of(userData.getObjectById(userData.getId1())));
//    }

    @Test
    void getAllByCompany() {
        SCHOOL_MATCHER.assertMatch(service.getAll(companyData.getId1()), testData.getAllByCompany(companyData.getId1()));
    }

//    @Test
//    void addUserInSchool() {
//        School school = service.getWithUsers(testData.getId1());
//        //проверяем, что связь есть только по одному ученику
//        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()),
//                List.of(userData.getObjectById(userData.getId1())));
//        SchoolUsers su = createSchoolUser(school, userData.getId2());
//        school.setUsers(Set.of(su));
//        service.update(school);
//        school = service.getWithUsers(testData.getId1());
//        //проверяем, что связь появилась по двум ученикам
//        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()), userData.getAll());
//    }

//    @Test
//    void updateUserSchool() {
//        School school = service.getWithUsers(testData.getId1());
//        //проверяем, что связь есть только по одному ученику
//        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()),
//                List.of(userData.getObjectById(userData.getId1())));
//        SchoolUsers su = school.getUsers().iterator().next();
//        //проверяем, что связь с параметром enable = true
//        Assertions.assertThat(su.getEnable()).as("Enable должно быть равно true").isTrue();
//        su.setEnable(false);
//        school.setUsers(Set.of(su));
//        service.update(school);
//        school = service.getWithUsers(testData.getId1());
//        //проверяем, что связь есть только по одному ученику
//        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()),
//                List.of(userData.getObjectById(userData.getId1())));
//        su = school.getUsers().iterator().next();
//        //проверяем, что связь с параметром enable = false
//        Assertions.assertThat(su.getEnable()).as("Enable должно быть равно false").isFalse();
//    }
//
//    @Test
//    void updateSchoolsWithoutSchoolUsers() {
//        School school = service.getWithUsers(testData.getId1());
//        //проверяем, что связь есть только по одному ученику
//        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()),
//                List.of(userData.getObjectById(userData.getId1())));
//        School update = testData.getUpdate();
//        service.update(update);
//        school = service.getWithUsers(testData.getId1());
//        SCHOOL_MATCHER.assertMatch(school, update);
//        //проверяем, что связь есть только по одному ученику
//        USER_TEST_MATCHER.assertMatch(userData.getUserFromSchoolUsers(school.getUsers()),
//                List.of(userData.getObjectById(userData.getId1())));
//    }

//    @Test
//    void getAllEmployees() {
//        Set<SchoolEmployees> employees = service.getWithEmployees(testData.getId1()).getEmployees();
//        Employee emp = employeeData.getEmployeeFromSchoolEmployees(employees).get(0);
//        EMPLOYEE_MATCHER.assertMatch(emp, employeeData.getObjectById(employeeData.getId1()));
//    }
//
//    @Test
//    void addEmployeesToSchool() {
//        School school = service.getWithEmployees(testData.getId1());
//        SchoolEmployees se1 = createSchoolEmployee(school, employeeData.getId2());
//        SchoolEmployees se2 = createSchoolEmployee(school, employeeData.getId3());
//        //добавили еще двух преподавателей
//        school.getEmployees().addAll(List.of(se1, se2));
//        service.update(school);
//        school = service.getWithEmployees(testData.getId1());
//        List<Employee> employees = employeeData.getEmployeeFromSchoolEmployees(school.getEmployees());
//        employees.sort(Comparator.comparing(Employee::id));
//        EMPLOYEE_MATCHER.assertMatch(employees, employeeData.getAll());
//    }
//
//    @Test
//    void updateEmployeeConnect() {
//        School school = service.getWithEmployees(testData.getId1());
//        SchoolEmployees se = school.getEmployees().iterator().next();
//        Assertions.assertThat(se.getEnable()).as("Enable должно быть равно true").isTrue();
//        se.setEnable(false);
//        school.setEmployees(Set.of(se));
//        service.update(school);
//        school = service.getWithEmployees(testData.getId1());
//        se = school.getEmployees().iterator().next();
//        Assertions.assertThat(se.getEnable()).as("Enable должно быть равно false").isFalse();
//    }
//
//    @Test
//    void updateSchoolWithoutEmployees() {
//        School school = service.getWithEmployees(testData.getId1());
//        List<Employee> employees = employeeData.getEmployeeFromSchoolEmployees(school.getEmployees());
//        EMPLOYEE_MATCHER.assertMatch(employees, List.of(employeeData.getObjectById(employeeData.getId1())));
//        school = testData.getUpdate();
//        service.update(school);
//        school = service.getWithEmployees(testData.getId1());
//        employees = employeeData.getEmployeeFromSchoolEmployees(school.getEmployees());
//        EMPLOYEE_MATCHER.assertMatch(employees, List.of(employeeData.getObjectById(employeeData.getId1())));
//    }
//
//    private SchoolUsers createSchoolUser(School school, Long userId) {
//        return new SchoolUsers(school, userData.getObjectById(userId));
//    }
//
//    private SchoolEmployees createSchoolEmployee(School school, Long employeeId) {
//        return new SchoolEmployees(school, employeeData.getObjectById(employeeId));
//    }
}
