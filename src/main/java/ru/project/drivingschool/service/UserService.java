package ru.project.drivingschool.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.embedded.SchoolUserId;
import ru.project.drivingschool.model.link.UserRoles;
import ru.project.drivingschool.repository.SchoolRepository;
import ru.project.drivingschool.repository.UserRepository;
import ru.project.drivingschool.to.ResultPage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.project.drivingschool.util.ValidationUtil.*;

@Service
public class UserService extends AbstractService<User> //implements UserDetailsService
{
    private final UserRepository repository;
    
    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ResultPage<User> getAll(int offset, int limit, String phone, String email, String lastname, String firstname) {
        log.info("Get all entity from page={} and limit={}", offset, limit);
        return super.getAll(offset, limit, Example.of(getExample(phone, email, lastname, firstname)));
    }

    public ResultPage<User> getAll(String phone, String email, String lastname, String firstname) {
        return super.getAll(Example.of(getExample(phone, email, lastname, firstname)));
    }

    public User create(User user, Long createdBy) {
        log.info("Create user {}. Created={}", user.toString(), createdBy);
        checkNew(user);
        return this.save(user, createdBy);
    }

    @Override
    public User update(User user, Long changedBy) {
        log.info("Update user {}. Changed={}", user.toString(), changedBy);
        if(StringUtils.isEmpty(user.getPassword())) {
            User old = super.get(user.id());
            user.setPassword(old.getPassword());
        }
        user.getRoles().forEach(ur->setLinkInRoles(ur, user));
        return checkNotFoundWithId(this.save(user, changedBy), user.getId());
    }

    private User save(User user, long createdBy) {
        checkNotNull(user);
        return repository.save(user, createdBy);
    }

    private void setLinkInRoles(UserRoles ur, User u) {
        School school = ur.getSchool();
        ur.setId(new SchoolUserId(school.getId(), u.getId()));
        ur.setUser(u);
    }

//    @Override
//    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
//        log.info("authenticate user by phone {}", phone);
//        User u = this.repository.getByPhone(phone);
//        if (Objects.isNull(u))
//            throw new UsernameNotFoundException(String.format("User %s is not found", phone));
//        return new AuthorizedUser(u);
//    }

    @Override
    User getExample(boolean active) {
        User u = new User();
        u.setActive(active);
        u.setHistory(null);
        return u;
    }

    User getExample(String phone, String email, String lastname, String firstname) {
        User u = new User();
        u.setHistory(null);
        u.setEmailStatus(null);
        u.setPhoneStatus(null);
        u.setActive(null);
        u.setScore(null);
        u.setPhone(phone);
        u.setEmail(email);
        u.setLastname(lastname);
        u.setFirstname(firstname);
        return u;
    }
}
