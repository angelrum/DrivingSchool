package ru.project.drivingschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.project.drivingschool.AuthorizedUser;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.repository.UserRepository;

import java.util.List;
import java.util.Objects;

import static ru.project.drivingschool.util.ValidationUtil.*;

@Service
public class UserService extends AbstractService<User> implements UserDetailsService {
    
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

//    public List<User> getAll() {
//        log.info("get all users");
//        return repository.getAll();
//    }
    
    public User getByPhone(String phone){
        checkNotNull(phone);
        return checkNotFound(repository.getByPhone(phone), String.format("Not found with phone=%s", phone));
    }

    public User create(User user, long createdBy) {
        log.info("Create user {}. Created={}", user.toString(), createdBy);
        checkNew(user);
        return this.save(user, createdBy);
    }

    public User update(User user, long changedBy) {
        log.info("Update user {}. Changed={}", user.toString(), changedBy);
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
        String bpass = this.passwordEncoder.encode(u.getPassword());
        log.info("New bPass {} for password {}", bpass, u.getPassword());
        u.setPassword(bpass);
        return new AuthorizedUser(u);
    }
}
