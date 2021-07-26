package ru.project.drivingschool.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.drivingschool.model.Contract;

import java.util.List;
import java.util.Set;

public interface JpaContractRepository extends JpaKeyRepository<Contract> {

    @Query("SELECT cu.contract FROM CompanyUsers cu WHERE cu.user.id=:userId ORDER BY cu.contract.id")
    Page<Contract> getUserContracts(@Param("userId") Long userId, Pageable page);

    @Query("SELECT c FROM Contract c LEFT JOIN FETCH c.parent WHERE c.id=:id")
    Contract get (@Param("id") Long id);

    @Query("SELECT c FROM Contract c WHERE c.parent.id=:id")
    List<Contract> getChild(@Param("id") Long id);
}
