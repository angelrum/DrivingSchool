package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.embedded.SchoolUsers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class UserTestData implements TestDataInterface<User>{

    public static final long USER_ID1 = 1003;
    public static final long USER_ID2 = 1004;
    public static final long NOT_FOUND_ID = 99999;
    public static final TestMatcher<User> USER_TEST_MATCHER = TestMatcher.usingFieldsComparator(User.class, "history", "schools");


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
        userNew.setPhone("8(912)111-11-11");
        userNew.setFirstname("Новый пользователь");
        return userNew;
    }

    @Override
    public User getUpdate() {
        User upd = new User(User2);
        upd.setFirstname("Обновленный пользователь");
        return upd;
    }

    public List<User> getUserFromSchoolUsers(Set<SchoolUsers> su) {
        return su.stream().map(SchoolUsers::getUser).collect(Collectors.toList());
    }
}
