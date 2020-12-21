package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.embedded.SchoolUsers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class UserTestData implements TestDataInterface<User>{

    public static final TestMatcher<User> USER_TEST_MATCHER = TestMatcher.usingFieldsComparator(User.class);
    private final long userId1 = 1000;
    private final long userId2 = 1001;
    private final long userId3 = 1002;
    private final long userId4 = 1003;
    private final long userId5 = 1004;
    private final long userId6 = 1005;
    private final long userId7 = 1006;
    private final long userId8 = 1007;
    private final long userId9 = 1008;

    public static final long NOT_FOUND_ID = 99999;

    //Админы
    private User User1 = new User(userId1, "+7(911)111-11-11", true, "12345", null, "Иван", "Иванов", "Иванович", "admin1@test.ru", true, User.DEF_SCORE, true, null, null, Role.ADMIN);
    private User User2 = new User(userId2, "+7(911)111-11-12", true, "123456", null, "Антон", "Иванов", "Иванович", "admin2@test.ru", true, User.DEF_SCORE, true, null, null, Role.ADMIN);
    //учитель в ООО "Рога и копыта"
    private User User3 = new User(userId3, "+7(911)111-11-13", true, "123457", null, "Сергей", "Петров", "Иванович", "teacher1@test.ru", true, User.DEF_SCORE, true, null, null, Role.TEACHER);
    private User User4 = new User(userId4, "+7(911)111-11-14", true, "123458", null, "Петр", "Петров", "Иванович", "teacher2@test.ru", true, User.DEF_SCORE, true, null, null, Role.TEACHER);
    //учитель в ООО "Новые рога"
    private User User5 = new User(userId5, "+7(911)111-11-15", true, "123459", null, "Виктор", "Иванов", "Иванович", "teacher1@test1.ru", true, User.DEF_SCORE, true, null, null, Role.TEACHER);
    //ученики в ООО "Рога и копыта"
    private User User6 = new User(userId6, "+7(911)211-11-11", true, "12345", null, "Артем", "Антонов", "Иванович", "student1@test1.ru", true, User.DEF_SCORE, true, null, null, Role.STUDENT);
    private User User7 = new User(userId7, "+7(911)211-11-12", true, "123456", null, "Иван", "Антонов", "Иванович", "student2@test1.ru", true, User.DEF_SCORE, true, null, null, Role.STUDENT);
    private User User8 = new User(userId8, "+7(911)211-11-13", true, "1234567", null, "Иван", "Антонов", "Иванович", "student3@test1.ru", true, User.DEF_SCORE, true, null, null, Role.STUDENT);
    //ученики в ООО "Новые рога"
    private User User9 = new User(userId9, "+7(911)211-11-14", true, "12345678", null, "Сергей", "Антонов", "Иванович", "student4@test1.ru", true, User.DEF_SCORE, true, null, null, Role.STUDENT);

    @Override
    public long getId1() {
        return userId1;
    }

    @Override
    public long getId2() {
        return userId2;
    }

    @Override
    public Collection<User> getAll() {
        return new ArrayList<>(List.of(User1, User2, User3, User4, User5, User6, User7, User8, User9));
    }

    @Override
    public User getNew() {
        User userNew = new User(User6);
        userNew.setId(null);
        userNew.setPhone("8(912)111-11-11");
        userNew.setFirstname("Новый пользователь");
        return userNew;
    }

    @Override
    public User getUpdate() {
        User upd = new User(User6);
        upd.setFirstname("Обновленный пользователь");
        return upd;
    }

    public List<User> getUserFromSchoolUsers(Set<SchoolUsers> su) {
        return su.stream().map(SchoolUsers::getUser).collect(Collectors.toList());
    }
}
