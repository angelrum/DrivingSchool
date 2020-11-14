package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.model.Role;
import ru.project.drivingschool.model.School;

import java.util.*;

import static ru.project.drivingschool.testdata.CompanyTestData.*;
import static ru.project.drivingschool.testdata.SchoolTestData.*;

public class EmployeeTestData {

    public static final TestMatcher<Employee> EMPLOYEE_MATCHER =
            //TestMatcher.usingClassComparator(Employee.class, Map.of(Comparator.comparing(Employee::id), Employee.class, Comparator.comparing(School::getId), School.class), "company", "createdOn", "changedOn", "createdBy", "school");
            TestMatcher.usingFieldsComparator(Employee.class, "company", "createdOn", "changedOn", "createdBy", "schools");

    public static final long EMPLOYEE_ID1 = 1_000;
    public static final long EMPLOYEE_ID2 = 1_001;

    // (10000, '+7(911)111-11-11', '12345', null, 'Иванов', 'Иван', 'Иванович', 'test1@test.ru', null),
    // (10000, '+7(911)111-11-12', '123456', null, 'Иванов', 'Антон', 'Иванович', 'test2@test.ru', 1000);

    public static Employee EMPLOYEE1 = new Employee(EMPLOYEE_ID1, COMPANY1, "+7(911)111-11-11", "12345", null, "Иванов", "Иван", "Иванович", "test1@test.ru", true, Employee.DEF_SCORE, null, null, null, null, Set.of(SCHOOL1, SCHOOL2), Role.ADMIN);
    public static Employee EMPLOYEE2 = new Employee(EMPLOYEE_ID2, COMPANY1, "+7(911)111-11-12", "123456", null, "Иванов", "Антон", "Иванович", "test2@test.ru", true, Employee.DEF_SCORE, null, EMPLOYEE1, null, null, null, Role.MANAGER);

    public static List<Employee> EMPLOYEES = List.of(EMPLOYEE1, EMPLOYEE2);

    public static Employee getNew() {
        Employee emp = new Employee(EMPLOYEE1);
        emp.setId(null);
        emp.setPhone("+7(911)111-11-13");
        emp.setFirstname("Петров");
        emp.setCreatedBy(EMPLOYEE2);
        return emp;
    }

    public static Employee getUpdate() {
        Employee upd = new Employee(EMPLOYEE2);
        upd.setPhone("+7(911)111-11-14");
        return upd;
    }
}
