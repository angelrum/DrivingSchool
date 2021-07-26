package ru.project.drivingschool.to;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.link.CompanyUsers;
import ru.project.drivingschool.model.link.SchoolUsers;
import ru.project.drivingschool.model.link.UserRoles;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserTo extends BaseTo {

    protected Set<UserRolesTo> roles;

    protected String avatar;

    @NotBlank protected String firstname;

    protected String middlename;

    @NotBlank protected String lastname;

    protected LocalDate birthdate;

    @Email protected String email;

    protected Boolean emailStatus = false;

    @NotBlank protected String phone;

    protected Boolean phoneStatus = false;

    protected Boolean active = true;

    protected Integer score = User.DEF_SCORE;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String password;

    protected Set<UserSchoolTo> schools;

    public UserTo(Long id, @NotBlank String phone, Boolean phoneStatus, String avatar,
                  @NotBlank String firstname, @NotBlank String lastname, String middlename,
                  @Email String email, Boolean emailStatus, Integer score, Boolean active, String password, Set<UserRolesTo> roles, Set<UserSchoolTo> schools) {
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
        this.password = u.getPassword();
        setRoles(u.getRoles());
        setSchools(u.getSchoolUsers(), u.getCompanyUsers());
    }

    public UserTo(User u, Set<SchoolUsers> su, Set<CompanyUsers> cu) {
        this(u);
        setSchools(su, cu);
    }

    public void setRoles(Set<UserRoles> userRoles) {
        Map<Long, List<Role>> map = new HashMap<>();
        userRoles.forEach(ur -> {
            List<Role> roles = map.computeIfAbsent(ur.getSchool().id(), k -> new ArrayList<>());
            roles.add(ur.getRole());
        });
        this.roles = map.entrySet().stream()
                .map(entry -> new UserRolesTo(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }
    private void setSchools(Set<SchoolUsers> schoolUsers, Set<CompanyUsers> companyUsers) {
        Set<UserSchoolTo> usTos = new HashSet<>();
        schoolUsers.forEach(su -> {
            CompanyUsers companyUser = companyUsers.stream()
                    .filter(cu -> cu.getCompany().getId().equals(su.getSchool().getCompany().getId()))
                    .findFirst().orElse(null);
            if (Objects.nonNull(companyUser))
                usTos.add(new UserSchoolTo(su, companyUser));
        });
        this.schools = usTos;
    }
}
