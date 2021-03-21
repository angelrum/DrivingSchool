package ru.project.drivingschool.model;

import lombok.*;
import ru.project.drivingschool.model.common.HasId;
import ru.project.drivingschool.model.directory.Country;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "address")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    protected Country country = Country.RUS;

    protected String postalCode;

    protected String region;

    @NotBlank protected String city;

    protected String settlement;

    @NotBlank protected String street;

    protected String house;

    protected Integer floor;

    protected String office;

    protected String latitude;

    protected String longitude;

    protected Integer qcGeo = 5;

    public Address(Address a) {
        this(a.id, a.country, a.postalCode, a.region, a.city, a.settlement, a.street, a.house, a.floor, a.office, a.latitude, a.longitude, a.qcGeo);
    }
}
