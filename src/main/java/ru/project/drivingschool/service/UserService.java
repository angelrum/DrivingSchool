package ru.project.drivingschool.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.project.drivingschool.AuthorizedUser;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.repository.UserRepository;

import java.util.Objects;

import static ru.project.drivingschool.util.ValidationUtil.*;

@Service
public class UserService extends AbstractService<User> implements UserDetailsService {
    
    private UserRepository repository;
    
    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }
    
    public User getByPhone(String phone){
        checkNotNull(phone);
        return checkNotFound(repository.getByPhone(phone), String.format("Not found with phone=%s", phone));
    }

    @Override
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
        return this.save(user, changedBy);
    }

    private User save(User user, long createdBy) {
        checkNotNull(user);
        return repository.save(user, createdBy);
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        log.info("authenticate user by phone {}", phone);
        User u = this.repository.getByPhone(phone);
        if (Objects.isNull(u))
            throw new UsernameNotFoundException(String.format("User %s is not found", phone));
        return new AuthorizedUser(u);
    }
}
