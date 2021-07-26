package ru.project.drivingschool.model.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.util.DateTimeUtil;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

//https://www.baeldung.com/jpa-embedded-embeddable
@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {

    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    protected LocalDateTime createdOn = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    protected User createdBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    protected LocalDateTime changedOn;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    protected User changedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof History)) return false;
        History history = (History) o;
        return createdOn.equals(history.createdOn) && Objects.equals(changedOn, history.changedOn);
    }

    public History(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdOn, changedOn);
    }

    @Override
    public String toString() {
        return "History{" +
                "createdOn=" + createdOn +
                ", changedOn=" + changedOn +
                '}';
    }
}
