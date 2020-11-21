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

    @Column(name = "school_id")
    private Long schoolId;

    @Column(name = "user_id")
    private Long userId;

    public SchoolUserId(@NonNull Long schoolId, @NonNull Long userId) {
        this.schoolId = schoolId;
        this.userId = userId;
    }
}
