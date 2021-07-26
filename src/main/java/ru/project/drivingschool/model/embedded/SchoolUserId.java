package ru.project.drivingschool.model.embedded;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//https://stackoverflow.com/questions/45411890/jpa-how-to-cascade-save-entities-when-child-has-embedded-id
@Embeddable
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class SchoolUserId implements Serializable {

    private Long schoolId;

    private Long userId;

    public SchoolUserId(@NonNull Long schoolId, Long userId) {
        this.schoolId = schoolId;
        this.userId = userId;
    }
}
