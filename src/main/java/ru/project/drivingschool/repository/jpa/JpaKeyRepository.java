package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface JpaKeyRepository <T> extends JpaRepository<T, Long> {

    @Modifying
    @Query("DELETE FROM #{#entityName} t WHERE t.id=:id")
    int delete(@Param("id") long id);
}
