package ru.project.drivingschool.model.link;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Status;
import ru.project.drivingschool.model.embedded.AbstractHistoryEmbedded;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.model.embedded.SchoolUserId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "school_users")
@Getter @Setter
@NoArgsConstructor
public class SchoolUsers extends AbstractHistoryEmbedded {

    @EmbeddedId
    protected SchoolUserId id;

    @ManyToOne
    @MapsId("schoolId")
    protected School school;

    @ManyToOne
    @MapsId("userId")
    protected User user;

    @Enumerated(EnumType.STRING)
    protected Status status;

    public SchoolUsers(School school, User user, Status status, History history) {
        super(history);
        this.id = new SchoolUserId(school.getId(), user.getId());
        this.school = school;
        this.user = user;
        this.setStatus(status);
    }

    public SchoolUsers(School school, User user, Status status) {
        super();
        this.id = new SchoolUserId(school.getId(), user.getId());
        this.school = school;
        this.user = user;
        this.setStatus(status);
    }

    public void setStatus(Status status) {
        this.status = Objects.isNull(status) ? Status.PENDING : status;
    }

    public void setSchool(School school) {
        this.school = school;
        this.id.setSchoolId(school.getId());
    }

    public void setUser(User user) {
        this.user = user;
        this.id.setUserId(user.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolUsers)) return false;
        SchoolUsers that = (SchoolUsers) o;
        return id.equals(that.id) &&
                Objects.equals(school, that.school) &&
                Objects.equals(user, that.user) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getSchoolId(), id.getUserId(), status);
    }
}
