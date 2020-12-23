package ru.project.drivingschool.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.directory.Country;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public class CompanyTo extends BaseTo {

    @NotBlank protected String name;

    protected String shortName;

    @NotBlank protected String phone;

    protected String email;

    protected String website;

    protected Boolean active = true;

    protected Address addressLegal;

    protected Address adressActual;

    public CompanyTo(Long id, @NotBlank String name, String shortName, @NotBlank String phone, String email, String website, Boolean active, Address addressLegal, Address adressActual) {
        super(id);
        this.name = name;
        this.shortName = shortName;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.active = active;
        this.addressLegal = addressLegal;
        this.adressActual = adressActual;
    }

    public CompanyTo(Company c) {
        super(c.id());
        this.name = c.getName();
        this.shortName = c.getShortName();
        this.phone = c.getPhone();
        this.email = c.getEmail();
        this.website = c.getWebsite();
        this.active = c.getActive();
        this.addressLegal = c.getAddressLegal();
        this.adressActual = c.getAdressActual();
    }
}
