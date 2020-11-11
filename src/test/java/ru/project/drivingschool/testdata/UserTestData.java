package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.User;

import java.awt.desktop.UserSessionEvent;
import java.util.List;

public class UserTestData {

    public static final TestMatcher<User> USER_TEST_MATCHER = TestMatcher.usingFieldsComparator(User.class, "createdOn");

    public static final long USER_ID1 = 10000;
    public static final long USER_ID2 = 10001;
    public static final long USER_ID3 = 10002;
    public static final long NOT_FOUND_ID = 99999;

    public static User User1 = new User(USER_ID1, "8(911)111-11-11", "testpsw",
            "ne znayu", "Иван", "Иванов", "Иванович", "ivan@ivan.ru",
            true, null, null,
            null, null);
    public static User User2 = new User(USER_ID2, "8(911)111-11-12", "testpsw",
            "ne znayu", "Сергей", "Иванов", "Иванович", "sergey@ivan.ru",
            true, null, null,
            null, null);
    public static User User3 = new User(USER_ID3, "8(911)111-11-13", "testpsw",
            "ne znayu", "Евлампий", "Блябляс", "Цырендоржиевич", "qwe@ivan.ru",
            true, null, null,
            null, null);

    public static List<User> Users = List.of(User1, User2, User3);

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

}