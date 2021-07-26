package ru.project.drivingschool.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.Company;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompanyTo extends BaseTo {

    @NotBlank protected String name;

    protected String shortName;

    protected Address addressLegal;

    protected Address addressActual;

    @NotBlank protected String phone;

    protected String email;

    protected String website;

    protected Boolean active = true;

    public CompanyTo(Long id, @NotBlank String name, String shortName, @NotBlank String phone,
                     String email, String website, Boolean active, Address addressLegal, Address addressActual) {
        super(id);
        this.name = name;
        this.shortName = shortName;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.active = active;
        this.addressLegal = addressLegal;
        this.addressActual = addressActual;
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
        this.addressActual = c.getAddressActual();
    }
}
