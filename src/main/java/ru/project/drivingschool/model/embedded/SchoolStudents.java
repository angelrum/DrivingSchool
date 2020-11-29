package ru.project.drivingschool.model.embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "school_students")
@Getter @Setter
@NoArgsConstructor
public class SchoolStudents {

    @EmbeddedId
    protected SchoolUserId id;

    @ManyToOne
    @MapsId("schoolId")
    protected School school;

    @ManyToOne
    @MapsId("userId")
    protected User user;

    protected Boolean enable = true;

    public SchoolStudents(School school, User user) {
        this.id = new SchoolUserId();
        this.school = school;
        this.user = user;
    }

    public SchoolStudents(School school, User user, Boolean enable) {
        this(school, user);
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolStudents)) return false;
        SchoolStudents that = (SchoolStudents) o;
        return id.equals(that.id) &&
                Objects.equals(school, that.school) &&
                Objects.equals(user, that.user) &&
                enable.equals(that.enable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getSchoolId(), id.getUserId(), enable);
    }
}