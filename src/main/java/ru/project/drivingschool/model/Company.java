package ru.project.drivingschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.drivingschool.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "companys")
@Getter @Setter
@NoArgsConstructor
public class Company implements HasId {

    @Id
    protected Long id;

    @Column(name = "name")
    @NotBlank
    protected String name;

    @Enumerated(EnumType.STRING)
    protected Country country;

    @Column(name = "city")
    protected String city;

    @Column(name = "street")
    protected String street;

    @Column(name = "home")
    protected String home;

    @Column(name = "postal_code")
    protected String postalCode;

    @Column(name = "phone")
    @NotBlank
    protected String phone;

    @Column(name = "email")
    protected String email;

    @Column(name = "created_on")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    protected LocalDateTime createdOn;

    public Company(Long id, @NotBlank String name, Country country, String city, String street, String home, String postalCode, @NotBlank String phone, String email, LocalDateTime createdOn) {
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
    }
}
