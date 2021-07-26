package ru.project.drivingschool.model.link;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.Contract;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Status;
import ru.project.drivingschool.model.embedded.AbstractHistoryEmbedded;
import ru.project.drivingschool.model.embedded.CompanyUserId;
import ru.project.drivingschool.model.embedded.History;

import javax.persistence.*;

@Entity
@Table(name = "company_users")
@Getter @Setter
@NoArgsConstructor
public class CompanyUsers extends AbstractHistoryEmbedded {

    @EmbeddedId
    protected CompanyUserId id;

    @ManyToOne
    @MapsId("companyId")
    protected Company company;

    @ManyToOne
    @MapsId("userId")
    protected User user;

    @OneToOne(targetEntity = Contract.class, cascade = CascadeType.ALL)
    protected Contract contract;

    @Enumerated(EnumType.STRING)
    protected Status status;

    public CompanyUsers(Company company, User user, Status status, History history) {
        super(history);
        this.company = company;
        this.user = user;
        this.status = status;
    }
}
