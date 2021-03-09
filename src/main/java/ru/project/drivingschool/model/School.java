package ru.project.drivingschool.model;


import lombok.*;
import org.springframework.util.CollectionUtils;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.model.embedded.SchoolUsers;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "schools")
@Getter @Setter @ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class School extends AbstractKeyHistoryEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    protected Company company;

    @NotBlank protected String name;

    @Column(name = "short_name")
    protected String shortName;

    protected String phone;

    protected String email;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    protected Address address;

    protected Boolean active = true;

    //https://www.baeldung.com/jpa-many-to-many
    //https://vladmihalcea.com/merge-entity-collections-jpa-hibernate/
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    @ToString.Exclude protected Set<SchoolUsers> schoolUsers;

    public School(Long id, @NotNull Company company, @NotBlank String name, String shortName, String phone, String email, Address address, Boolean active, Set<SchoolUsers> schoolUsers, History history) {
        super(id, history);
        this.company = company;
        this.name = name;
        this.shortName = shortName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.active = Objects.isNull(active) ? true : active;
        setUsers(schoolUsers);
    }

    public School(School s) {
        this(s.id, s.company, s.name, s.shortName, s.phone, s.email, s.address, s.active, s.schoolUsers, s.history);
    }

    public void setUsers(Set<SchoolUsers> schoolUsers) {
        this.schoolUsers = CollectionUtils.isEmpty(schoolUsers) ? new HashSet<>() : schoolUsers;
    }

}
