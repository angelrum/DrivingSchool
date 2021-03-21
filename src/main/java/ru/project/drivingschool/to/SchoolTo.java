package ru.project.drivingschool.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.School;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SchoolTo extends BaseTo {

//    @NotNull protected Long companyId;

    @NotBlank protected String name;

    protected String shortName;

    protected String phone;

    protected String email;

    protected Address address;

    protected Boolean active = true;

    public SchoolTo(Long id, @NotBlank String name, String shortName, String phone, String email, Address address, Boolean active) {
        super(id);
        this.name = name;
        this.shortName = shortName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.active = active;
    }

    public SchoolTo(School s) {
        super(s.id());
//        this.companyId = s.getCompany().id();
        this.name = s.getName();
        this.shortName = s.getShortName();
        this.phone = s.getPhone();
        this.email = s.getEmail();
        this.address = s.getAddress();
        this.active = s.getActive();
    }
}
