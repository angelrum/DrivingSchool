package ru.project.drivingschool.model.embedded;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
class SchoolEmployeeId implements Serializable {

    @Column(name = "school_id")
    private Long schoolId;

    @Column(name = "user_id")
    private Long userId;
}
