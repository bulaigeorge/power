package cegal.power.CityInfo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "city_info")
public class CityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String city;
    private int totalConsumption, totalEmission, totalCost;

    private List<String> months;

    public CityInfo() {};

    public CityInfo(String city, int totalConsumption, int totalEmission, int totalCost, List<String> months) {
        this.city = city;
        this.totalConsumption = totalConsumption;
        this.totalEmission = totalEmission;
        this.totalCost = totalCost;
        this.months = months;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(int totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public int getTotalEmission() {
        return totalEmission;
    }

    public void setTotalEmission(int totalEmission) {
        this.totalEmission = totalEmission;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> emissionPerMonth) {
        this.months = emissionPerMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityInfo cityInfo = (CityInfo) o;
        return id == cityInfo.id && totalConsumption == cityInfo.totalConsumption && totalEmission == cityInfo.totalEmission && totalCost == cityInfo.totalCost && city.equals(cityInfo.city) && months.equals(cityInfo.months);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, totalConsumption, totalEmission, totalCost, months);
    }
}
