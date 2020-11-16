package ru.project.drivingschool.model;

import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "employees")
@Getter @Setter @ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Employee extends AbstractNamedEntity {

    public static final int DEF_SCORE = 5;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ToString.Exclude protected Company company;

    @Column(name = "score")
    protected Integer score = 5;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<Role> roles;

    //https://www.codejava.net/frameworks/hibernate/hibernate-one-to-many-association-on-join-table-annotations-example
    @OneToMany(fetch = FetchType.EAGER, targetEntity = School.class)
    @JoinTable(
            name = "school_employees",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "school_id", referencedColumnName = "id"))
    protected Set<School> schools;

    public Employee(Long id, Company company, @NotBlank String phone, @NotBlank String password, String avatar,
                    @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, boolean enabled, Integer score,
                    LocalDateTime createdOn, Employee createdBy, LocalDateTime changedOn, Employee changedBy, Set<School> schools, Collection<Role> roles) {
        super(id, phone, password, avatar, firstname, lastname, middlename, email, enabled, createdOn, createdBy, changedOn, changedBy);
        this.company = company;
        setScore(score);
        setRoles(roles);
        setSchools(schools);
    }

    public Employee(Long id, Company company, @NotBlank String phone, @NotBlank String password, String avatar,
                    @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, boolean enabled, Integer score,
                    LocalDateTime createdOn, Employee createdBy, LocalDateTime changedOn, Employee changedBy, Set<School> schools, Role role, Role... roles) {
        this(id, company, phone, password, avatar, firstname, lastname, middlename, email, enabled, score, createdOn, createdBy, changedOn, changedBy, schools, EnumSet.of(role, roles));
    }

    public Employee(Employee e) {
        this(e.id, e.company, e.phone, e.password, e.avatar, e.firstname, e.lastname, e.middlename, e.email, e.enabled, e.score, e.createdOn, e.createdBy, e.changedOn, e.changedBy, e.schools, e.roles);

    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public void setScore(Integer score) {
        this.score = Objects.isNull(score) ? DEF_SCORE : score;
    }

    public void setSchools(Set<School> schools) {
        this.schools = CollectionUtils.isEmpty(schools) ? new HashSet<>() : schools;
    }
}
