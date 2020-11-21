package ru.project.drivingschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter @ToString(callSuper = true)
@NoArgsConstructor
public class User extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, targetEntity = School.class)
    @JoinTable(
            name = "school_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "school_id", referencedColumnName = "id"))
    @ToString.Exclude protected Set<School> schools;

    public User(Long id, @NotBlank String phone, @NotBlank String password, String avatar,
                @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, boolean enabled,
                LocalDateTime createdOn, Employee createdBy, LocalDateTime changedOn, Employee changedBy,Set<School> schools) {
        super(id, phone, password, avatar, firstname, lastname, middlename, email, enabled, createdOn, createdBy, changedOn, changedBy);
        setSchools(schools);
    }

    public User(User u) {
        this(u.id, u.phone, u.password, u.avatar, u.firstname, u.lastname, u.middlename,
                u.email, u.enabled, u.createdOn, u.createdBy, u.changedOn, u.changedBy, u.schools);
    }

    public void setSchools(Set<School> schools) {
        this.schools = CollectionUtils.isEmpty(schools) ? new HashSet<>() : schools;;
    }
}
