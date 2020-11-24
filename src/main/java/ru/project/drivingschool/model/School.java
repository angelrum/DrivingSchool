package ru.project.drivingschool.model;


import lombok.*;
import org.springframework.util.CollectionUtils;
import ru.project.drivingschool.model.embedded.SchoolEmployees;
import ru.project.drivingschool.model.embedded.SchoolUsers;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "schools")
@Getter @Setter @ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class School extends AbstractHistoryEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @NotNull protected Company company;

    @NotBlank protected String name;

    @NotBlank protected String city;

    @NotBlank protected String street;

    @NotBlank protected String home;

    @NotBlank protected String postalCode;

    protected String phone;

    protected String email;

    protected boolean enabled = true;

    //https://www.baeldung.com/jpa-many-to-many
    //https://vladmihalcea.com/merge-entity-collections-jpa-hibernate/
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    @ToString.Exclude protected Set<SchoolUsers> users;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    @ToString.Exclude protected Set<SchoolEmployees> employees;

    public School(Long id, @NotNull Company company, @NotBlank String name, @NotBlank String city, @NotBlank String street, @NotBlank String home, @NotBlank String postalCode,
                  String phone, String email, boolean enabled, Set<SchoolUsers> users, Set<SchoolEmployees> employees, LocalDateTime createdOn, Employee createdBy, LocalDateTime changedOn, Employee changedBy) {
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

    public void setUsers(Set<SchoolUsers> users) {
        this.users = CollectionUtils.isEmpty(users) ? new HashSet<>() : users;
    }

    public void setEmployees(Set<SchoolEmployees> employees) {
        this.employees = CollectionUtils.isEmpty(employees) ? new HashSet<>() : employees;
    }
}
