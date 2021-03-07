package ru.project.drivingschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.model.embedded.History;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "companys")
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

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_legal_id", referencedColumnName = "id")
    protected Address addressLegal;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_actual_id", referencedColumnName = "id")
    protected Address addressActual;

    @OneToMany(targetEntity = School.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude protected Set<School> schools;

    public Company(Long id, @NotBlank String name, String shortName, @NotBlank String phone, String email, String website, Boolean active, Address addressLegal, Address addressActual, Set<School> schools, History history) {
        super(id, history);
        this.name = name;
        this.shortName = shortName;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.active = Objects.isNull(active) ? true : active;
        this.addressLegal = addressLegal;
        this.addressActual = addressActual;
        setSchools(schools);
    }

    public Company(Company c) {
        this(c.id, c.name, c.shortName, c.phone, c.email, c.website, c.active, c.addressLegal, c.addressActual, c.schools, c.history);
    }

    public void setSchools(Set<School> schools) {
        this.schools = Objects.isNull(schools) ? new HashSet<>(): schools;
    }
}
