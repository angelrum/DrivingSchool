package ru.project.drivingschool.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.testdata.CompanyTestData;
import ru.project.drivingschool.testdata.EmployeeTestData;
import java.util.List;

import static ru.project.drivingschool.testdata.EmployeeTestData.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest extends AbstractServiceTest<Employee> {

    private EmployeeService service;

    private CompanyTestData companyTestData;

    private EmployeeTestData testData;

    @Autowired
    EmployeeServiceTest(EmployeeService service) {
        super(service, new EmployeeTestData(), EMPLOYEE_MATCHER);
        this.service = service;
        this.testData = new EmployeeTestData();
        this.companyTestData = new CompanyTestData();
    }

    //что бы сравнивать schools необходимо из сравнения исключить поля createdBy и createdOn
    //это будет сделано после реализации тестов для ServiceSchools
    @Override
    @Test
    void getAll() {
        super.getAll();
        List<Employee> actual = service.getAll();
        for (int i = 0; i < actual.size(); i++)
            Assertions.assertThat(actual.get(i).getSchools())
                    .hasSize(testData.getAll().get(i).getSchools().size());
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