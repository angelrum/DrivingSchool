package ru.project.drivingschool.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.project.drivingschool.TimingExtension;
import ru.project.drivingschool.model.Employee;

import java.util.ArrayList;
import java.util.List;

import static ru.project.drivingschool.testdata.EmployeeTestData.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(TimingExtension.class)
@Sql(scripts = "classpath:db/data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
class EmployeeServiceTest {

    @Autowired
    protected EmployeeService service;

    //что бы сравнить schools нужно наложиь внутренние ограничения на проверки по полям createdBy и createdOn
    //сделаем это при реализации тестов для школы, пока сравниваем по кол-ву объектов
    @Test
    void getAll() {
        List<Employee> actual = service.getAll();
        EMPLOYEE_MATCHER.assertMatch(actual, EMPLOYEES);
        for (int i = 0; i < actual.size(); i++)
            Assertions.assertThat(actual.get(i).getSchools()).hasSize(EMPLOYEES.get(i).getSchools().size());
    }

    @Test
    void get() {
        EMPLOYEE_MATCHER.assertMatch(service.get(EMPLOYEE_ID1), EMPLOYEE1);
    }

    @Test
    void create() {
        Employee employee = getNew();
        Employee create = service.create(employee);
        employee.setId(create.getId());
        List<Employee> expected = new ArrayList<>(EMPLOYEES);
        expected.add(employee);
        EMPLOYEE_MATCHER.assertMatch(service.getAll(), expected);
    }

    @Test
    void update() {
        Employee expected = getUpdate();
        service.update(expected);
        EMPLOYEE_MATCHER.assertMatch(service.get(expected.id()), expected);
        Assertions.assertThat(service.getAll()).hasSize(EMPLOYEES.size());
    }

    @Test
    void delete() {
        service.delete(EMPLOYEE_ID1);
        EMPLOYEE_MATCHER.assertMatch(service.getAll(), List.of(EMPLOYEE2));
    }

}