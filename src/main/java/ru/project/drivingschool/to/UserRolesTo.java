package ru.project.drivingschool.to;

import lombok.*;
import ru.project.drivingschool.model.directory.Role;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserRolesTo {

    private Long schoolId;

    private List<Role> type;

}
