package ru.project.drivingschool.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "schools")
@Getter @Setter @ToString
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

    public School(Long id, Company company, @NotBlank String name, @NotBlank String city, @NotBlank String street, @NotBlank String home, @NotBlank String postalCode,
                  String phone, String email, boolean enabled, LocalDateTime createdOn, Employee createdBy, LocalDateTime changedOn, Employee changedBy) {
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
    }
}
