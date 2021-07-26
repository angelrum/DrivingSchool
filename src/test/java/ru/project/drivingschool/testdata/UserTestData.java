package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.embedded.SchoolUserId;
import ru.project.drivingschool.model.link.SchoolUsers;
import ru.project.drivingschool.model.link.UserRoles;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static ru.project.drivingschool.testdata.SchoolTestData.*;

public class UserTestData implements TestDataInterface<User> {

    public static final TestMatcher<User> USER_TEST_MATCHER = TestMatcher.usingFieldsComparator(User.class, "schoolUsers", "history", "roles", "companyUsers");
    public static final TestMatcher<UserRoles> USER_ROLES_MATCHER = TestMatcher.usingFieldsComparator(UserRoles.class, "history", "user", "school");

    public static final long USER_ID_1 = 1000L;
    public static final long USER_ID_2 = 1001L;
    public static final long USER_ID_3 = 1007L;
    public static final long USER_ID_4 = 1008L;
    public static final long USER_ID_5 = 1009L;
    public static final long USER_ID_6 = 1010L;
    public static final long USER_ID_7 = 1011L;

    public static final long NOT_FOUND_ID = 99999L;

    public SchoolTestData schoolTestData = new SchoolTestData();

    //Админы
    private User user1 = new User(USER_ID_1, "Иван", "Иванов", "Иванович", LocalDate.of(2001, 1,1),
            "admin1@test.ru", false, "+7(911) 111-11-11", false, "https://img.icons8.com/bubbles/100/000000/elvis-presley.png", "12345",
            true, 0, null, null);
    private User user2 = new User(USER_ID_2, "Антон", "Иванов", "Иванович", LocalDate.of(2002, 2, 1),
            "admin2@test.ru", false, "+7(911) 111-11-12", false, null, "123456",
            true, 0, null, null);
    //Менеджеры
    private User user3 = new User(USER_ID_3, "Петр", "Петров", "Иванович", LocalDate.of(2001, 2, 1),
            "manager1@test.ru", false, "+7(911) 111-11-13", false, null, "123456",
            true, 0, null, null);
    private User user4 = new User(USER_ID_4, "Петр", "Иванов", "Иванович", LocalDate.of(2001, 2, 1),
            "manager2@test.ru", false, "+7(911) 111-11-14", false, null, "123456",
            true, 0, null, null);
    //учитель
    private User user5 = new User(USER_ID_5, "Алексей", "Петров", "Иванович", LocalDate.of(1989, 2, 1),
            "teacher@test.ru", false, "+7(911) 111-11-15", false, null, "123456",
            true, 0, null, null);
    private User user6 = new User(USER_ID_6, "Роман", "Петров", "Иванович", LocalDate.of(2001, 2, 1),
            "teacher2@test.ru", false, "+7(911) 111-11-16", false, null, "123456",
            true, 0, null, null);
    private User user7 = new User(USER_ID_7, "Александр", "Иванов", "Иванович", LocalDate.of(2001, 2, 1),
            "teacher3@test.ru", false, "+7(911) 111-11-17", false, null, "123456",
            true, 0, null, null);

    public UserTestData() {
        UserRoles ur1 = new UserRoles(new SchoolUserId(SCHOOL_ID_1, USER_ID_1), schoolTestData.getObjectById(SCHOOL_ID_1), this.user1, Role.ADMIN);
        user1.setRoles(Set.of(ur1));
        UserRoles ur2_1 = new UserRoles(new SchoolUserId(SCHOOL_ID_2, USER_ID_2), schoolTestData.getObjectById(SCHOOL_ID_2), this.user2, Role.ADMIN);
        UserRoles ur2_2 = new UserRoles(new SchoolUserId(SCHOOL_ID_3, USER_ID_2), schoolTestData.getObjectById(SCHOOL_ID_3), this.user2, Role.ADMIN);
        user2.setRoles(Set.of(ur2_1, ur2_2));
        UserRoles ur3 = new UserRoles(new SchoolUserId(SCHOOL_ID_1, USER_ID_3), schoolTestData.getObjectById(SCHOOL_ID_1), this.user3, Role.MANAGER);
        user3.setRoles(Set.of(ur3));
        UserRoles ur4_1 = new UserRoles(new SchoolUserId(SCHOOL_ID_2, USER_ID_4), schoolTestData.getObjectById(SCHOOL_ID_2), this.user4, Role.MANAGER);
        UserRoles ur4_2 = new UserRoles(new SchoolUserId(SCHOOL_ID_3, USER_ID_4), schoolTestData.getObjectById(SCHOOL_ID_3), this.user4, Role.MANAGER);
        user4.setRoles(new HashSet<>(Set.of(ur4_1, ur4_2)));
        UserRoles ur5 = new UserRoles(new SchoolUserId(SCHOOL_ID_1, USER_ID_5), schoolTestData.getObjectById(SCHOOL_ID_1), this.user5, Role.INSTRUCTOR);
        user5.setRoles(new HashSet<>(Set.of(ur5)));
        UserRoles ur6 = new UserRoles(new SchoolUserId(SCHOOL_ID_2, USER_ID_6), schoolTestData.getObjectById(SCHOOL_ID_2), this.user6, Role.INSTRUCTOR);
        user6.setRoles(Set.of(ur6));
        UserRoles ur7 = new UserRoles(new SchoolUserId(SCHOOL_ID_3, USER_ID_7), schoolTestData.getObjectById(SCHOOL_ID_3), this.user7, Role.INSTRUCTOR);
        user7.setRoles(Set.of(ur7));
    }

    @Override
    public long getId1() {
        return USER_ID_1;
    }

    @Override
    public long getId2() {
        return USER_ID_2;
    }

    @Override
    public Collection<User> getAll() {
        return new ArrayList<>(List.of(user1, user2, user3, user4, user5, user6, user7));
    }

    @Override
    public User getNew() {
        User userNew = new User(user6);
        userNew.setId(null);
        userNew.setPhone("8(912)111-11-11");
        userNew.setEmail("new@mail.ru");
        userNew.setFirstname("Новый пользователь");
        UserRoles ur = new UserRoles(null, schoolTestData.getObjectById(SCHOOL_ID_1), null, Role.TEACHER );
        userNew.setRoles(Set.of(ur));
        return userNew;
    }

    @Override
    public User getUpdate() {
        User upd = new User(user6);
        upd.setFirstname("Обновленный пользователь");
        return upd;
    }

    public List<User> getUserFromSchoolUsers(Set<SchoolUsers> su) {
        return su.stream().map(SchoolUsers::getUser).collect(Collectors.toList());
    }
}
