package ru.project.drivingschool;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.to.UserTo;

import java.util.HashSet;
import java.util.Set;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;
    private static final String ROLE_PREFIX = "ROLE_";

    @Getter private UserTo to;

    public AuthorizedUser(User user) {
        super(user.getPhone(), user.getPassword(), user.getActive(), true, true, true, getAuthority(user.getRoles()));
        this.to = new UserTo(user);
    }

    public static long getCompanyId() {
        return 10_000;
    }

    public static long getId() {
        return 1_000;
    }

    private static Set<GrantedAuthority> getAuthority(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.name())));
        return authorities;
    }
}
