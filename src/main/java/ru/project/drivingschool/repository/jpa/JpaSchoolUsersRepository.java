package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.drivingschool.model.embedded.SchoolUserId;
import ru.project.drivingschool.model.embedded.SchoolUsers;

import java.util.List;
import java.util.Set;

public interface JpaSchoolUsersRepository extends JpaRepository<SchoolUsers, SchoolUserId> {

    @Query("SELECT s FROM SchoolUsers s WHERE s.id.schoolId=:schoolId")
    Set<SchoolUsers> getBySchool(@Param("schoolId") Long schoolId);

    @Query("SELECT s FROM SchoolUsers s WHERE s.id.userId=:userId")
    Set<SchoolUsers> getByUser(@Param("userId")Long userId);

}
