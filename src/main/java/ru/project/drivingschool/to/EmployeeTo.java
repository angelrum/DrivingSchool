package ru.project.drivingschool.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.project.drivingschool.model.directory.Role;

import javax.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;
import java.util.Set;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public class EmployeeTo extends PersonTo {

    private Integer score;

    private Set<Role> roles;

    @ConstructorProperties({"id", "phone", "password", "avatar", "firstname", "lastname", "middlename", "email", "enabled", "score", "roles"})
    public EmployeeTo(Long id, @NotBlank String phone, @NotBlank String password, String avatar, @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, Boolean enabled, Integer score, Set<Role> roles) {
        super(id, phone, password, avatar, firstname, lastname, middlename, email, enabled);
        this.score = score;
        this.roles = roles;
    }
}
