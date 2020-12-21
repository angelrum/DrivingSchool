package ru.project.drivingschool.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.directory.Country;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public class CompanyTo extends BaseTo {

    @NotBlank private String name;

    @NotNull private Country country;

    private String city;

    private String street;

    private String home;

    private String postalCode;

    @NotBlank private String phone;

    private String email;

    public CompanyTo(Long id, @NotBlank String name, Country country, String city, String street, String home, String postalCode, @NotBlank String phone, String email) {
        super(id);
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.home = home;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
    }

    public CompanyTo(Company c) {

    }
}
