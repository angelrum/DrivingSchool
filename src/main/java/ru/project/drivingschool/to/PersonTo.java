package ru.project.drivingschool.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public class PersonTo extends BaseTo {

    @NotBlank protected String phone;

    @NotBlank protected String password;

    protected String avatar;

    @NotBlank protected String firstname;

    @NotBlank protected String lastname;

    protected String middlename;

    protected String email;

    protected Boolean enabled = true;

    public PersonTo() {
    }

    public PersonTo(Long id, @NotBlank String phone, @NotBlank String password, String avatar, @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, Boolean enabled) {
        super(id);
        this.phone = phone;
        this.password = password;
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.email = email;
        this.enabled = enabled;
    }
}
