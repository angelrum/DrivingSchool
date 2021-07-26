package ru.project.drivingschool.to;

import lombok.Data;
import ru.project.drivingschool.model.Index;
import java.util.List;

@Data
public class ResultPage<T> {
    private final Index index;
    private final List<T> result;
}
