package ru.project.drivingschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.model.embedded.SchoolUsers;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users")
@Getter @Setter @ToString(callSuper = true)
@NoArgsConstructor
public class User extends AbstractKeyHistoryEntity implements Serializable {

    public static final int DEF_SCORE = 0;

    @NotBlank protected String phone;

    protected Boolean phoneStatus = false;

    protected String password;

    protected String avatar;

    @NotBlank protected String firstname;

    @NotBlank protected String lastname;

    protected String middlename;

    @Email protected String email;

    protected Boolean emailStatus = false;

    protected Integer score = DEF_SCORE;

    protected Boolean active = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude protected Set<SchoolUsers> schoolUsers;

    public User(Long id, @NotBlank String phone, Boolean phoneStatus, @NotBlank String password, String avatar,
                @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, Boolean emailStatus,
                Integer score, Boolean active, Set<SchoolUsers> schoolUsers, History history, Set<Role> roles) {
        super(id, history);
        this.phone = phone;
        this.password = password;
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.email = email;
        setPhoneStatus(phoneStatus);
        setEmailStatus(emailStatus);
        setActive(active);
        setScore(score);
        setRoles(roles);
        setSchoolUsers(schoolUsers);
    }
    public User(Long id, @NotBlank String phone, Boolean phoneStatus, @NotBlank String password, String avatar,
                @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, Boolean emailStatus,
                Integer score, Boolean active, Set<SchoolUsers> schoolUsers, History history, Role... roles) {
        this(id, phone, phoneStatus, password, avatar, firstname, lastname, middlename, email, emailStatus, score, active, schoolUsers, history, Set.of(roles));
    }

    public User(User u) {
        this(u.id, u.phone, u.phoneStatus, u.password, u.avatar, u.firstname, u.lastname, u.middlename, u.email, u.emailStatus, u.score, u.active, u.schoolUsers, u.history, u.roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public void setScore(Integer score) {
        this.score = Objects.isNull(score) ? DEF_SCORE : score;
    }

    public void setPhoneStatus(Boolean phoneStatus) {
        this.phoneStatus = Objects.isNull(phoneStatus) ? false : phoneStatus;
    }

    public void setEmailStatus(Boolean emailStatus) {
        this.emailStatus = Objects.isNull(emailStatus) ? false : emailStatus;
    }

    public void setActive(Boolean active) {
        this.active = Objects.isNull(active) ? true : active;
    }

    public void setSchoolUsers(Set<SchoolUsers> schoolUsers) {
        this.schoolUsers = CollectionUtils.isEmpty(schoolUsers) ? new HashSet<>() : schoolUsers;
    }
}
