package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.User;


import java.util.Collection;


public class UserTestData implements TestDataInterface<User>{
    @Override
    public long getId1() {
        return 0;
    }

    @Override
    public long getId2() {
        return 0;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public User getNew() {
        return null;
    }

    @Override
    public User getUpdate() {
        return null;
    }
/*
    public static final TestMatcher<User> USER_TEST_MATCHER = TestMatcher.usingFieldsComparator(User.class, "createdOn");

    public static final long USER_ID1 = 1003;
    public static final long USER_ID2 = 1004;

    public static final long NOT_FOUND_ID = 99999;

    public static User User1 = new User(USER_ID1, "8(911)111-11-11", "12345",
            null, "Иван", "Иванов", "Иванович", "ivan@ivan.ru",
            true, null, null,
            null, null);
    public static User User2 = new User(USER_ID2, "8(911)111-11-12", "123456",
            null, "Сергей", "Иванов", "Иванович", "serg@ivan.ru",
            true, null, null,
            null, null);


    public static List<User> Users = List.of(User1, User2);

    public static User getNewUser() {
        User userNew = new User(User1);
        userNew.setId(null);
        userNew.setFirstname("Новый пользователь");
        return userNew;
    }

    public static User getUpdateUser() {
        User upd = new User(User2);
        upd.setFirstname("Обновленный пользователь");
        return upd;
    }

 */
}
