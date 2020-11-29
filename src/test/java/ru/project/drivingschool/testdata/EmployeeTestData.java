package ru.project.drivingschool.testdata;

import ru.project.drivingschool.model.Role;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.embedded.History;

import java.util.Collection;
import java.util.List;


public class EmployeeTestData implements TestDataInterface<User> {

    private final long employeeId1 = 1000L;

    private final long employeeId2 = 1001L;

    private final long employeeId3 = 1002L;

    private CompanyTestData companyData = new CompanyTestData();

//        (10000, '+7(911)111-11-11', '12345', 'Иван', 'Иванов', 'Иванович', 'test1@test.ru', 0, null),
//        (10000, '+7(911)111-11-12', '123456', 'Антон', 'Иванов', 'Иванович', 'test2@test.ru', 0, 1000),
//        (10001, '+7(911)111-11-13', '123456', 'Сергей', 'Иванов', 'Иванович', 'test3@test.ru', 0, null),

    public User Employee1 = new User(employeeId1, companyData.getObjectById(companyData.getId1()), User.DEF_SCORE, "+7(911)111-11-11", "12345", null, "Иван", "Иванов", "Иванович", "test1@test.ru", true, null, null, null, Role.ADMIN);

    public User Employee2 = new User(employeeId2, companyData.getObjectById(companyData.getId1()), User.DEF_SCORE, "+7(911)111-11-12", "123456", null, "Антон", "Иванов", "Иванович", "test2@test.ru", true, null, null, new History(null, Employee1, null, null), Role.MANAGER);

    public User Employee3 = new User(employeeId3, companyData.getObjectById(companyData.getId2()), User.DEF_SCORE, "+7(911)111-11-13", "123456", null, "Сергей", "Иванов", "Иванович", "test3@test.ru", true, null, null, null, Role.ADMIN);

    @Override
    public long getId1() {
        return employeeId1;
    }

    @Override
    public long getId2() {
        return employeeId2;
    }

    @Override
    public Collection<User> getAll() {
        return List.of(Employee1, Employee2, Employee3);
    }

    @Override
    public User getNew() {
        return null;
    }

    @Override
    public User getUpdate() {
        return null;
    }
}
