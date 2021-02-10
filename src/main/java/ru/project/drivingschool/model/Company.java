package ru.project.drivingschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.drivingschool.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "companys")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Company implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank protected String name;

    @Enumerated(EnumType.STRING)
    protected Country country;

    protected String city;

    protected String street;

    protected String home;

    @Column(name = "postal_code")
    protected String postalCode;

    @NotBlank protected String phone;

    protected String email;

    @OneToMany(targetEntity = School.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    protected Set<School> schools;

    @Column(name = "created_on", updatable = false)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    @CreationTimestamp
    protected LocalDateTime createdOn;

    public Company(Long id, @NotBlank String name, Country country, String city, String street, String home, String postalCode, @NotBlank String phone, String email, Set<School> schools, LocalDateTime createdOn) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.home = home;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.createdOn = createdOn;
        setSchools(schools);
    }

    public Company(Company c) {
        this(c.id, c.name, c.country, c.city, c.street, c.home, c.postalCode, c.phone, c.email, c.schools, c.createdOn);
    }

    public void setSchools(Set<School> schools) {
        this.schools = Objects.isNull(schools) ? new HashSet<>(): schools;
    }
}
