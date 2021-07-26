package ru.project.drivingschool.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.embedded.SchoolUserId;
import ru.project.drivingschool.model.link.UserRoles;
import ru.project.drivingschool.testdata.SchoolTestData;
import ru.project.drivingschool.testdata.UserTestData;

import java.util.*;

import static ru.project.drivingschool.testdata.SchoolTestData.SCHOOL_ID_2;
import static ru.project.drivingschool.testdata.UserTestData.*;

@SpringBootTest
class UserServiceTest extends AbstractServiceTest<User> {

    private UserService service;
    private UserTestData testData;
    private SchoolTestData schoolTestData;

    @Autowired
    UserServiceTest(UserService service) {
        super(service, new UserTestData(), USER_TEST_MATCHER);
        this.service = service;
        this.testData = new UserTestData();
        this.schoolTestData = new SchoolTestData();
    }

    @Test
    void checkRoles() {
        List<User> users = service.getAll().getResult();
        users.forEach(u -> roleMatches(u.getRoles(), testData.getObjectById(u.getId()).getRoles()));
    }

    @Test
    void insertNewRoles() {
        User user = service.get(USER_ID_5);
        UserRoles ur = new UserRoles(new SchoolUserId(SCHOOL_ID_2, USER_ID_5), this.schoolTestData.getObjectById(SCHOOL_ID_2), user, Role.INSTRUCTOR);
        Set<UserRoles> urs = user.getRoles();
        urs.add(ur);
        service.update(user, USER_ID_1);
        user = service.get(USER_ID_5);
        roleMatches(urs, user.getRoles());
    }

    @Test
    void updateRoles() {
        User user = service.get(USER_ID_5);
        Set<UserRoles> urs = user.getRoles();
        urs.forEach(ur -> ur.setRole(Role.TEACHER));
        service.update(user, USER_ID_1);
        user = service.get(USER_ID_5);
        roleMatches(urs, user.getRoles());
    }
    @Test
    void deleteAllRoles() {
        User user = service.get(USER_ID_5);
        user.setRoles(new HashSet<>());
        service.update(user, USER_ID_1);
        user = service.get(USER_ID_5);
        Assertions.assertThat(user.getRoles()).isEmpty();
    }

    @Test
    void addNewRole() {
        User user = testData.getObjectById(USER_ID_5);
        user.getRoles().add(new UserRoles(new SchoolUserId(), schoolTestData.getObjectById(SCHOOL_ID_2), user, Role.TEACHER));
        service.update(user, USER_ID_1);
        roleMatches(user.getRoles(), service.get(USER_ID_5).getRoles());
    }

    @Test
    void removeOneRole() {
        User user = testData.getObjectById(USER_ID_4);
        UserRoles ur = user.getRoles().stream().findFirst().orElse(null);
        user.getRoles().remove(ur);
        service.update(user, USER_ID_1);
        roleMatches(user.getRoles(), service.get(USER_ID_4).getRoles());
    }

    void roleMatches(Set<UserRoles> act, Set<UserRoles> exp) {
        List<UserRoles> actual = new ArrayList<>(act);
        List<UserRoles> expected = new ArrayList<>(exp);
        actual.sort(Comparator.comparing(userRoles -> userRoles.getSchool().getId()));
        expected.sort(Comparator.comparing(userRoles -> userRoles.getSchool().getId()));
        USER_ROLES_MATCHER.assertMatch(actual, expected);
    }
}
