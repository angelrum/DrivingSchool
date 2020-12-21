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

    protected String region;

    @NotBlank protected String city;

    protected String zip;

    @NotBlank protected String street;

    protected String building;

    protected String home;

    protected Integer floor;

    protected Integer office;

    protected String latitude;

    protected String longitude;

    public Address(Address a) {
        this(a.id, a.country, a.region, a.city, a.zip, a.street, a.building, a.home, a.floor, a.office, a.latitude, a.longitude);
    }
}
