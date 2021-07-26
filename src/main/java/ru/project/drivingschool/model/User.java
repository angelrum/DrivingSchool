package ru.project.drivingschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.model.link.CompanyUsers;
import ru.project.drivingschool.model.link.SchoolUsers;
import ru.project.drivingschool.model.link.UserRoles;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
@Getter @Setter @ToString(callSuper = true)
@NoArgsConstructor
public class User extends AbstractKeyHistoryEntity implements Serializable {

    public static final int DEF_SCORE = 0;

    private static final String PHONE_FORMAT = "+X(XXX) XXX-XX-XX";

    @NotBlank protected String firstname;

    @NotBlank protected String lastname;

    protected String middlename;

    protected LocalDate birthdate;

    @Email protected String email;

    protected Boolean emailStatus = false;

    @NotBlank protected String phone;

    protected Boolean phoneStatus = false;

    protected String avatar;

    protected String password;

    protected Boolean active = true;

    protected Integer score = DEF_SCORE;

    @OneToMany(targetEntity = UserRoles.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @ToString.Exclude protected Set<UserRoles> roles;

    @OneToMany(targetEntity = SchoolUsers.class, mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude protected Set<SchoolUsers> schoolUsers;

    @OneToMany(targetEntity = CompanyUsers.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude protected Set<CompanyUsers> companyUsers;

    public User(Long id, String firstname, String lastname, String middlename,
                LocalDate birthdate, String email, Boolean emailStatus, String phone, Boolean phoneStatus,
                String avatar, String password, Boolean active, Integer score, Set<UserRoles> roles, History history) {
        super(id, history);
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.password = password;
        this.roles = roles;
        this.setScore(score);
        this.setPhoneStatus(phoneStatus);
        this.setEmailStatus(emailStatus);
        this.setActive(active);
    }

    public User(String firstname, String lastname, String middlename, LocalDate birthdate,
                String email, String phone, String avatar, String password, Set<UserRoles> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.password = password;
        this.roles = roles;
    }

    public User(User u) {
        this(u.getId(), u.getFirstname(), u.getLastname(), u.getMiddlename(),
                u.getBirthdate(), u.getEmail(), u.getEmailStatus(), u.getPhone(), u.getPhoneStatus(),
                u.getAvatar(), u.getPassword(), u.getActive(), u.getScore(), u.getRoles(), u.getHistory());
    }

//    public void setScore(Integer score) {
//        this.score = Objects.isNull(score) ? DEF_SCORE : score;
//    }
//
//    public void setPhoneStatus(Boolean phoneStatus) {
//        this.phoneStatus = !Objects.isNull(phoneStatus) && phoneStatus;
//    }
//
//    public void setEmailStatus(Boolean emailStatus) {
//        this.emailStatus = !Objects.isNull(emailStatus) && emailStatus;
//    }
//
//    public void setActive(Boolean active) {
//        this.active = Objects.isNull(active) || active;
//    }

    public void setPhone(String phone) {
        if (StringUtils.hasText(phone)) {
            String cl = phone.replaceAll("[^0-9]", "");
            String convertPhone = PHONE_FORMAT;
            for (int i = 0; i < cl.length(); i++)
                convertPhone = convertPhone.replaceFirst("X", String.valueOf(cl.charAt(i)));
            this.phone = convertPhone.contains("X") ? phone.trim() : convertPhone;
        }
    }
}
