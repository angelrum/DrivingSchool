package ru.project.drivingschool.model;


import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "schools")
@Getter @Setter @ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class School extends AbstractHistoryEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ToString.Exclude protected Company company;

    @Column(name = "name")
    @NotBlank
    protected String name;

    @Column(name = "city")
    @NotBlank
    protected String city;

    @Column(name = "street")
    @NotBlank
    protected String street;

    @Column(name = "home")
    @NotBlank
    protected String home;

    @Column(name = "postal_code")
    @NotBlank
    protected String postalCode;

    @Column(name = "phone")
    protected String phone;

    @Column(name = "email")
    protected String email;

    @Column(name = "enabled")
    protected boolean enabled = true;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinTable(
            name = "school_users",
            joinColumns = @JoinColumn(name = "school_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    @ToString.Exclude protected List<User> users;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Employee.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "school_employees",
            joinColumns = @JoinColumn(name = "school_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    @ToString.Exclude protected List<Employee> employees;

    public School(Long id, Company company, @NotBlank String name, @NotBlank String city, @NotBlank String street, @NotBlank String home, @NotBlank String postalCode,
                  String phone, String email, boolean enabled, List<User> users, List<Employee> employees, LocalDateTime createdOn, Employee createdBy, LocalDateTime changedOn, Employee changedBy) {
        super(id, createdOn, createdBy, changedOn, changedBy);
        this.company = company;
        this.name = name;
        this.city = city;
        this.street = street;
        this.home = home;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.enabled = enabled;
        setUsers(users);
        setEmployees(employees);
    }

    public School(School s) {
        this(s.id, s.company, s.name, s.city, s.street, s.home, s.postalCode,
                s.phone, s.email, s.enabled, s.users, s.employees, s.createdOn, s.createdBy, s.changedOn, s.changedBy);
    }

    public void setUsers(List<User> users) {
        this.users = CollectionUtils.isEmpty(users) ? new ArrayList<>() : users;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = CollectionUtils.isEmpty(employees) ? new ArrayList<>() : employees;
    }
}
