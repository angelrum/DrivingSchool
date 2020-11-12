package ru.project.drivingschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class User extends AbstractNamedEntity {

    public User(Long id, @NotBlank String phone, @NotBlank String password, String avatar,
                @NotBlank String firstname, @NotBlank String lastname, String middlename, String email, boolean enabled,
                LocalDateTime createdOn, Employee createdBy, LocalDateTime changedOn, Employee changedBy) {
        super(id, phone, password, avatar, firstname, lastname, middlename, email, enabled, createdOn, createdBy, changedOn, changedBy);
    }

    public User(User user1) {
        this(user1.id, user1.phone, user1.password, user1.avatar, user1.firstname, user1.lastname, user1.middlename, user1.email, user1.enabled, user1.createdOn, user1.createdBy, user1.changedOn, user1.changedBy);
    }
}
