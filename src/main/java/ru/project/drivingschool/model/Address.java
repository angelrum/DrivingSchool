package ru.project.drivingschool.model;

import lombok.*;
import ru.project.drivingschool.model.common.HasId;
import ru.project.drivingschool.model.directory.Country;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "address")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address implements HasId, Serializable {

    private static final long serialVersionUID = 1L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return id.equals(address.id) && country == address.country
                && Objects.equals(postalCode, address.postalCode)
                && region.equals(address.region) && city.equals(address.city)
                && Objects.equals(settlement, address.settlement)
                && street.equals(address.street) && house.equals(address.house)
                && Objects.equals(floor, address.floor)
                && Objects.equals(office, address.office)
                && Objects.equals(latitude, address.latitude)
                && Objects.equals(longitude, address.longitude)
                && qcGeo.equals(address.qcGeo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, postalCode, region, city, settlement, street, house, floor, office, latitude, longitude, qcGeo);
    }
}
