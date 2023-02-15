package cegal.power.CityInfo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city_info")
public class CityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String city;
    private int totalConsumption, totalEmission, totalCost;

    private List<String> emissionPerMonth;

    public CityInfo() {};

    public CityInfo(String city, int totalConsumption, int totalEmission, int totalCost) {
        this.city = city;
        this.totalConsumption = totalConsumption;
        this.totalEmission = totalEmission;
        this.totalCost = totalCost;
        this.emissionPerMonth = new ArrayList<>();
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

    public List<String> getEmissionPerMonth() {
        return emissionPerMonth;
    }

    public void setEmissionPerMonth(List<String> emissionPerMonth) {
        this.emissionPerMonth = emissionPerMonth;
    }
}
