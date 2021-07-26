package ru.project.drivingschool.model;

import lombok.*;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.model.link.CompanyUsers;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "companies")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Company extends AbstractKeyHistoryEntity {

    @NotBlank protected String name;

    @Column(name = "short_name")
    protected String shortName;

    @NotBlank protected String phone;

    protected String email;

    protected String website;

    protected Boolean active = true;

    @ManyToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    protected Address addressLegal;

    //cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    @ManyToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    protected Address addressActual;

    @OneToMany(targetEntity = School.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude protected Set<School> schools;

    @OneToMany(targetEntity = CompanyUsers.class, mappedBy = "company", cascade = CascadeType.ALL)
    @ToString.Exclude protected Set<CompanyUsers> companyUsers;

    public Company(Long id, @NotBlank String name, String shortName, @NotBlank String phone, String email,
                   String website, Boolean active, Address addressLegal, Address addressActual,
                   Set<School> schools, Set<CompanyUsers> companyUsers, History history) {
        super(id, history);
        this.name = name;
        this.shortName = shortName;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.active = Objects.isNull(active) || active;
        this.addressLegal = addressLegal;
        this.addressActual = addressActual;
        setSchools(schools);
        setCompanyUsers(companyUsers);
    }

    public Company(@NonNull Company c) {
        this(c.id, c.getName(), c.getShortName(), c.getPhone(), c.getEmail(),
                c.getWebsite(), c.getActive(), new Address(c.getAddressLegal()), new Address(c.getAddressActual()),
                c.getSchools(), c.getCompanyUsers(), c.getHistory());
    }

    public void setSchools(Set<School> schools) {
        this.schools = Objects.isNull(schools) ? new HashSet<>(): schools;
    }

    public void setCompanyUsers(Set<CompanyUsers> companyUsers) {
        this.companyUsers = Objects.isNull(companyUsers) ? new HashSet<>() : companyUsers;
    }
}
