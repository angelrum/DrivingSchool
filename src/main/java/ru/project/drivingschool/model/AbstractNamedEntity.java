package ru.project.drivingschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.project.drivingschool.model.embedded.History;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @ToString(callSuper = true)
@NoArgsConstructor
public abstract class AbstractNamedEntity extends AbstractHistoryEntity {

    @NotBlank protected String phone;

    @NotBlank protected String password;

    protected String avatar;

    @NotBlank protected String firstname;

    @NotBlank protected String lastname;

    protected String middlename;

    protected String email;

    protected boolean enabled = true;

    public AbstractNamedEntity(Long id, @NotBlank String phone, @NotBlank String password, String avatar,
                               @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, boolean enabled,
                               LocalDateTime createdOn, Employee createdBy, LocalDateTime changedOn, Employee changedBy) {
        super(id, new History(createdOn, createdBy, changedOn, changedBy));
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
