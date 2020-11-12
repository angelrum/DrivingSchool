package ru.project.drivingschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @ToString(callSuper = true)
@NoArgsConstructor
public abstract class AbstractNamedEntity extends AbstractHistoryEntity {

    @Column(name = "phone")
    @NotBlank
    protected String phone;

    @Column(name = "password")
    @NotBlank
    protected String password;

    @Column(name = "avatar")
    protected String avatar;

    @Column(name = "firstname")
    @NotBlank
    protected String firstname;

    @Column(name = "lastname")
    @NotBlank
    protected String lastname;

    @Column(name = "middlename")
    protected String middlename;

    @Column(name = "email")
    protected String email;

    protected boolean enabled = true;

    public AbstractNamedEntity(Long id, @NotBlank String phone, @NotBlank String password, String avatar,
                               @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, boolean enabled,
                               LocalDateTime createdOn, Employee createdBy, LocalDateTime changedOn, Employee changedBy) {
        super(id, createdOn, createdBy, changedOn, changedBy);
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
