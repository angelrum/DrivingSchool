package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.model.Role;

import java.util.*;

public class EmployeeTestData implements TestDataInterface<Employee> {

    public static final TestMatcher<Employee> EMPLOYEE_MATCHER =
            //TestMatcher.usingClassComparator(Employee.class, Map.of(Comparator.comparing(Employee::id), Employee.class, Comparator.comparing(School::getId), School.class), "company", "createdOn", "changedOn", "createdBy", "school");
            TestMatcher.usingFieldsComparator(Employee.class, "company", "createdOn", "changedOn", "createdBy", "schools");

    public EmployeeTestData() {
        CompanyTestData companyData = new CompanyTestData();
        SchoolTestData schoolData = new SchoolTestData();
        employee1 = new Employee(employeeId1, companyData.getObjectById(companyData.getId1()), "+7(911)111-11-11", "12345", null, "Иванов", "Иван", "Иванович", "test1@test.ru", true, Employee.DEF_SCORE, null, null, null, null, new HashSet<>(schoolData.getAll()), Role.ADMIN);
        employee2 = new Employee(employeeId2, companyData.getObjectById(companyData.getId1()), "+7(911)111-11-12", "123456", null, "Иванов", "Антон", "Иванович", "test2@test.ru", true, Employee.DEF_SCORE, null, employee1, null, null, null, Role.MANAGER);
        employee3 = new Employee(employeeId3, companyData.getObjectById(companyData.getId2()), "+7(911)111-12-13", "123456", null, "Иванов", "Сергей", "Иванович", "test3@test.ru", true, Employee.DEF_SCORE, null, null, null, null, null, Role.ADMIN);
    }

    private final long employeeId1 = 1_000;
    private final long employeeId2 = 1_001;

    private final long employeeId3 = 1_002;

    // (10000, '+7(911)111-11-11', '12345', null, 'Иванов', 'Иван', 'Иванович', 'test1@test.ru', null),
    // (10000, '+7(911)111-11-12', '123456', null, 'Иванов', 'Антон', 'Иванович', 'test2@test.ru', 1000);
    // (10001, '+7(911)111-12-13', '123456', null, 'Иванов', 'Сергей', 'Иванович', 'test3@test.ru', 5, null)

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;

    @Override
    public Employee getNew() {
        Employee emp = new Employee(employee2);
        emp.setId(null);
        emp.setPhone("+7(911)111-11-13");
        emp.setFirstname("Петров");
        emp.setCreatedBy(employee2);
        return emp;
    }

    @Override
    public Employee getUpdate() {
        Employee upd = new Employee(employee2);
        upd.setPhone("+7(911)111-11-14");
        return upd;
    }

    @Override
    public long getId1() {
        return employeeId1;
    }

    @Override
    public long getId2() {
        return employeeId2;
    }

    public long getId3() {
        return employeeId3;
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(List.of(employee1, employee2, employee3));
    }
}
