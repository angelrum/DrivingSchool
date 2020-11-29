package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Role;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.embedded.SchoolStudents;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class StudentTestData implements TestDataInterface<User>{

    public static final TestMatcher<User> USER_TEST_MATCHER = TestMatcher.usingFieldsComparator(User.class, "company", "history", "schoolEmployees", "schoolStudents");
    public final long studentId1 = 1003;
    public final long studentId2 = 1004;
    public final long studentId3 = 1005;

    public static final long NOT_FOUND_ID = 99999;
    public EmployeeTestData employeeData = new EmployeeTestData();

    public User Student1 = new User(studentId1, null, User.DEF_SCORE, "+7(911)111-12-11", "12345", null, "Иван", "Петров", "Иванович", "test4@test.ru", true, null, null, null, Role.STUDENT);
    public User Student2 = new User(studentId2, null, User.DEF_SCORE, "+7(911)111-12-12", "123456", null, "Антон", "Петров", "Иванович", "test5@test.ru", true, null, null, null, Role.STUDENT);
    public User Student3 = new User(studentId3, null, User.DEF_SCORE, "+7(911)111-12-13", "1234567", null, "Сергей", "Петров", "Иванович", "test6@test.ru", true, null, null, null, Role.STUDENT);


    @Override
    public long getId1() {
        return studentId1;
    }

    @Override
    public long getId2() {
        return studentId2;
    }

    @Override
    public Collection<User> getAll() {
        return new ArrayList<>(List.of(employeeData.Employee1, employeeData.Employee2, employeeData.Employee3, Student1, Student2, Student3));
    }

    @Override
    public User getNew() {
        User userNew = new User(Student1);
        userNew.setId(null);
        userNew.setPhone("8(912)111-11-11");
        userNew.setFirstname("Новый пользователь");
        return userNew;
    }

    @Override
    public User getUpdate() {
        User upd = new User(Student2);
        upd.setFirstname("Обновленный пользователь");
        return upd;
    }

    public List<User> getUserFromSchoolUsers(Set<SchoolStudents> su) {
        return su.stream().map(SchoolStudents::getUser).collect(Collectors.toList());
    }
}
