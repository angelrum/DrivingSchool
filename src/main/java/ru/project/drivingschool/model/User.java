package ru.project.drivingschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.model.embedded.SchoolEmployees;
import ru.project.drivingschool.model.embedded.SchoolStudents;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "users")
@Getter @Setter @ToString(callSuper = true)
@NoArgsConstructor
public class User extends AbstractHistoryEntity implements HasId {

    public static final int DEF_SCORE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ToString.Exclude protected Company company;

    protected Integer score = 0;

    @NotBlank protected String phone;

    @NotBlank protected String password;

    protected String avatar;

    @NotBlank protected String firstname;

    @NotBlank protected String lastname;

    protected String middlename;

    protected String email;

    protected boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude protected Set<SchoolEmployees> schoolEmployees;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude protected Set<SchoolStudents> schoolStudents;

    public User(Long id, Company company, Integer score, @NotBlank String phone, @NotBlank String password, String avatar,
                @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, boolean enabled,
                Set<SchoolEmployees> schoolEmployees, Set<SchoolStudents> schoolStudents, History history, Collection<Role> roles) {
        super(history);
        this.id = id;
        this.company = company;
        this.phone = phone;
        this.password = password;
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.email = email;
        this.enabled = enabled;
        setRoles(roles);
        setScore(score);
        setSchoolEmployees(schoolEmployees);
        setSchoolStudents(schoolStudents);
    }

    public User(Long id, Company company, Integer score, @NotBlank String phone, @NotBlank String password, String avatar,
                @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, boolean enabled,
                Set<SchoolEmployees> schoolEmployees, Set<SchoolStudents> schoolStudents, History history, Role role, Role...roles) {
        this(id, company, score, phone, password, avatar, firstname, lastname, middlename, email, enabled, schoolEmployees, schoolStudents, history, EnumSet.of(role, roles));
    }

    public User(User u) {
        this(u.id, u.company, u.score, u.phone, u.password, u.avatar, u.firstname, u.lastname, u.middlename, u.email, u.enabled, u.schoolEmployees, u.schoolStudents, u.history, u.roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public void setScore(Integer score) {
        this.score = Objects.isNull(score) ? DEF_SCORE : score;
    }

    public void setSchoolEmployees(Set<SchoolEmployees> schoolEmployees) {
        this.schoolEmployees = CollectionUtils.isEmpty(schoolEmployees) ? new HashSet<>() : schoolEmployees;
    }

    public void setSchoolStudents(Set<SchoolStudents> schoolStudents) {
        this.schoolStudents = CollectionUtils.isEmpty(schoolStudents) ? new HashSet<>() : schoolStudents;;
    }
}
