package ru.project.drivingschool.to;


import lombok.*;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.directory.Status;
import ru.project.drivingschool.model.link.CompanyUsers;
import ru.project.drivingschool.model.link.SchoolUsers;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserSchoolTo extends SchoolTo{

    private CompanyTo company;

    private Status statusSchool;

    private Status statusCompany;

    public UserSchoolTo(Long id, String name, String shortName, String phone, String email, Address address, Boolean active, CompanyTo company, Status statusSchool, Status statusCompany) {
        super(id, name, shortName, phone, email, address, active);
        this.company = company;
        this.statusSchool = statusSchool;
        this.statusCompany = statusCompany;
    }

    public UserSchoolTo(SchoolUsers su, CompanyUsers cu) {
        super(su.getSchool());
        this.company = new CompanyTo(su.getSchool().getCompany());
        this.statusSchool = su.getStatus();
        this.statusCompany = cu.getStatus();
    }
}
