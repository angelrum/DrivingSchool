package ru.project.drivingschool.model.embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "school_employees")
@Getter @Setter
@NoArgsConstructor
public class SchoolEmployees {

    @EmbeddedId
    private SchoolEmployeeId id;

    @ManyToOne
    @MapsId("schoolId")
    private School school;

    @ManyToOne
    @MapsId("userId")
    private User user;

    private Boolean enable = true;

    public SchoolEmployees(School school, User user) {
        this.id = new SchoolEmployeeId();
        this.school = school;
        this.user = user;
    }

    public SchoolEmployees(School school, User user, Boolean enable) {
        this(school, user);
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolEmployees)) return false;
        SchoolEmployees that = (SchoolEmployees) o;
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
