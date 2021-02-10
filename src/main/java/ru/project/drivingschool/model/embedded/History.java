package ru.project.drivingschool.model.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.util.DateTimeUtil;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

//https://www.baeldung.com/jpa-embedded-embeddable
@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {

    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    protected LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    protected Employee createdBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    protected LocalDateTime changedOn;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    protected Employee changedBy;

}
