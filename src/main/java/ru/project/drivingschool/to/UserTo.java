package ru.project.drivingschool.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.embedded.SchoolUsers;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserTo extends BaseTo {

    @NotBlank protected String phone;

    protected Boolean phoneStatus = false;

    protected String avatar;

    @NotBlank protected String firstname;

    @NotBlank protected String lastname;

    protected String middlename;

    protected String email;

    protected Boolean emailStatus = false;

    protected Integer score = User.DEF_SCORE;

    protected Boolean active = true;

    protected Set<Role> roles;

    protected Set<SchoolTo> schools;

    protected CompanyTo company;

    public UserTo(Long id, @NotBlank String phone, Boolean phoneStatus, String avatar,
                  @NotBlank String firstname, @NotBlank String lastname, String middlename,
                  String email, Boolean emailStatus, Integer score, Boolean active, Set<Role> roles, Set<SchoolTo> schools) {
        super(id);
        this.phone = phone;
        this.phoneStatus = phoneStatus;
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.email = email;
        this.emailStatus = emailStatus;
        this.score = score;
        this.active = active;
        this.roles = roles;
        this.schools = schools;
    }

    public UserTo(User u) {
        super(u.id());
        this.phone = u.getPhone();
        this.phoneStatus = u.getPhoneStatus();
        this.avatar = u.getAvatar();
        this.firstname = u.getFirstname();
        this.lastname = u.getLastname();
        this.middlename = u.getMiddlename();
        this.email = u.getEmail();
        this.emailStatus = u.getEmailStatus();
        this.score = u.getScore();
        this.active = u.getActive();
        this.roles = u.getRoles();
    }

    public UserTo(User u, Set<SchoolUsers> su) {
        this(u);
        setSchools(su);
    }

    private void setSchools(Set<SchoolUsers> su) {
        this.schools = su.stream()
                .map(SchoolUsers::getSchool)
                .map(SchoolTo::new)
                .collect(Collectors.toSet());
        su.stream()
                .findAny()
                .map(SchoolUsers::getSchool)
                .map(School::getCompany).ifPresent(this::setCompany);
    }

    public void setCompany(Company company) {
        this.company = new CompanyTo(company);
    }
}
