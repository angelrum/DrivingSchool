package ru.project.drivingschool.model.embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.model.School;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
public class SchoolEmployees {

    @EmbeddedId
    private SchoolEmployeeId id;

    @ManyToOne
    @MapsId("schoolId")
    private School school;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;

    private Boolean enable = true;

    public SchoolEmployees(School school, Employee employee) {
        this.id = new SchoolEmployeeId();
        this.school = school;
        this.employee = employee;
    }

    public SchoolEmployees(School school, Employee employee, Boolean enable) {
        this(school, employee);
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolEmployees)) return false;
        SchoolEmployees that = (SchoolEmployees) o;
        return id.equals(that.id) &&
                Objects.equals(school, that.school) &&
                Objects.equals(employee, that.employee) &&
                enable.equals(that.enable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getSchoolId(), id.getEmployeeId(), enable);
    }
}
