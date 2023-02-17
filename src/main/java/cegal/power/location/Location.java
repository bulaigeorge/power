package cegal.power.location;

import cegal.power.location.LocationDTOs.LocationDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String month, year, city, powerType, source;

    private int unitPrice, units;

    public Location() {};

    public Location(LocationDTO locationDTO) {
        this.month = locationDTO.month();
        this.year = String.valueOf(locationDTO.year());
        this.city = locationDTO.city();
        this.powerType = locationDTO.power();
        this.unitPrice = locationDTO.unitPrice();
        this.units = locationDTO.units();
        this.source = locationDTO.source();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id && unitPrice == location.unitPrice && units == location.units && month.equals(location.month) && year.equals(location.year) && city.equals(location.city) && powerType.equals(location.powerType) && source.equals(location.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, month, year, city, powerType, source, unitPrice, units);
    }
}