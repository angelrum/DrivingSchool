package ru.project.drivingschool.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.testdata.CompanyTestData;
import ru.project.drivingschool.testdata.EmployeeTestData;
import ru.project.drivingschool.testdata.SchoolTestData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.project.drivingschool.testdata.EmployeeTestData.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest extends AbstractServiceTest<Employee> {

    private EmployeeService service;

    private CompanyTestData companyTestData;

    private EmployeeTestData testData;

    private SchoolTestData schoolData;

    @Autowired
    EmployeeServiceTest(EmployeeService service) {
        super(service, new EmployeeTestData(), EMPLOYEE_MATCHER);
        this.service = service;
        this.testData = new EmployeeTestData();
        this.companyTestData = new CompanyTestData();
        this.schoolData = new SchoolTestData();
    }

    @Test
    void getWithSchools() {
        Set<School> schools = service.getWithSchools(testData.getId1()).getSchools();
        List<School> list = schools.stream()
                .sorted(Comparator.comparing(School::getId))
                .collect(Collectors.toList());
        SchoolTestData.SCHOOL_MATCHER.assertMatch(list, schoolData.getAll());
    }

    @Test
    void getByCompanyId() {
        EMPLOYEE_MATCHER.assertMatch(service.get(companyTestData.getId1(), testData.getId1()), testData.getObjectById(testData.getId1()));
    }

    @Test
    void getAllByCompanyId() {
        List<Employee> employees = List.of(testData.getObjectById(testData.getId1()), testData.getObjectById(testData.getId2()));
        EMPLOYEE_MATCHER.assertMatch(service.getAll(companyTestData.getId1()), employees);
    }

    @Test
    void deleteByCompanyId() {
        List<Employee> employees = List.of(testData.getObjectById(testData.getId1()), testData.getObjectById(testData.getId2()));
        EMPLOYEE_MATCHER.assertMatch(service.getAll(companyTestData.getId1()), employees);
        service.delete(companyTestData.getId1(), testData.getId2());
        EMPLOYEE_MATCHER.assertMatch(service.getAll(companyTestData.getId1()), List.of(testData.getObjectById(testData.getId1())));
    }

    @Test
    void createWithCompanyId() {
        Employee emp = testData.getNew();
        Employee create = service.create(emp, companyTestData.getId2(), testData.getId3());
        EMPLOYEE_MATCHER.assertMatch(create, service.get(companyTestData.getId2(), create.getId()));
    }

}