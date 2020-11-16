package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.drivingschool.model.Employee;

import java.util.List;

public interface JpaEmployeeRepository extends JpaNamedRepository<Employee> {

    List<Employee> getAllByCompanyIdOrderById(long companyId);

    Employee findFirstByCompanyIdAndId(long companyId, long id);

    @Query("DELETE FROM Employee WHERE company.id =:companyId AND id =:id")
    int delete(@Param("companyId") long companyId, @Param("id") long id);
}
