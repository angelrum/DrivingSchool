package ru.project.drivingschool.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.drivingschool.model.HasId;

@Getter @Setter
@NoArgsConstructor
public class BaseTo implements HasId {

    private Long id;

    public BaseTo(Long id) {
        this.id = id;
    }
}
