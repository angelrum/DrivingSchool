package ru.project.drivingschool.model.link;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.embedded.AbstractHistoryEmbedded;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.model.embedded.SchoolUserId;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class UserRoles {

    @EmbeddedId
    protected SchoolUserId id;

    @ManyToOne
    @MapsId("schoolId")
    protected School school;

    @ManyToOne
    @MapsId("userId")
    protected User user;

    @Enumerated(EnumType.STRING)
    protected Role role;

    public UserRoles(SchoolUserId id, School school, User user, Role role) {
        this.id = id;
        this.school = school;
        this.user = user;
        this.role = role;
    }

    public UserRoles(SchoolUserId id, Role role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "school=" + school.getId() +
                ", role=" + role +
                '}';
    }
}
