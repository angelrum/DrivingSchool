package ru.project.drivingschool.model.embedded;

import lombok.*;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CompanyUserId implements Serializable {

    private Long companyId;

    private Long userId;

    public CompanyUserId(@NonNull Long companyId, @NonNull Long userId) {
        this.companyId = companyId;
        this.userId = userId;
    }
}
