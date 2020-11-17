package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.User;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserTestData implements TestDataInterface<User>{

    public static final long USER_ID1 = 100;
    public static final long USER_ID2 = 101;
    public static final long NOT_FOUND_ID = 99999;
    public static final TestMatcher<User> USER_TEST_MATCHER = TestMatcher.usingFieldsComparator(User.class, "createdOn");


   // public static List<User> Users = List.of(User1, User2);

    public static User User1 = new User(USER_ID1, "8(911)111-12-11", "12345",
            null, "Иван", "Иванов", "Иванович", "ivan@ivan.ru",
            true, null, null,
            null, null, null);
    public static User User2 = new User(USER_ID2, "8(911)111-12-12", "123456",
            null, "Сергей", "Иванов", "Иванович", "serg@ivan.ru",
            true, null, null,
            null, null, null);


    @Override
    public long getId1() {
        return USER_ID1;
    }

    @Override
    public long getId2() {
        return USER_ID2;
    }

    @Override
    public Collection<User> getAll() {
        return new ArrayList<>(List.of(User1, User2));
    }

    @Override
    public User getNew() {
        User userNew = new User(User1);
        userNew.setId(null);
        userNew.setFirstname("Новый пользователь");
        return userNew;
    }

    @Override
    public User getUpdate() {
        User upd = new User(User2);
        upd.setFirstname("Обновленный пользователь");
        return upd;
    }

}
