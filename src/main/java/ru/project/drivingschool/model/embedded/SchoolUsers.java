package ru.project.drivingschool.model.embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Status;
import ru.project.drivingschool.util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "school_users")
@Getter @Setter
@NoArgsConstructor
public class SchoolUsers {

    @EmbeddedId
    protected SchoolUserId id;

    @ManyToOne
    @MapsId("schoolId")
    protected School school;

    @ManyToOne
    @MapsId("userId")
    protected User user;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    protected LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    protected LocalDate endDate;

    @Enumerated(EnumType.STRING)
    protected Status status;

    public SchoolUsers(School school, User user, LocalDate startDate, LocalDate endDate, Status status) {
        this.id = new SchoolUserId();
        this.school = school;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = Objects.isNull(status) ? Status.PENDING : status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolUsers)) return false;
        SchoolUsers that = (SchoolUsers) o;
        return id.equals(that.id) &&
                Objects.equals(school, that.school) &&
                Objects.equals(user, that.user) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getSchoolId(), id.getUserId(), startDate, endDate, status);
    }
}
