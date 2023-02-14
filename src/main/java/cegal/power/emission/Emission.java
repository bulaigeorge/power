package cegal.power.emission;

import jakarta.persistence.*;

@Entity
@Table(name = "emission")
public class Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type, emission;

    public Emission() {}

    public Emission(String type, String emission) {
        this.type = type;
        this.emission = emission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmission() {
        return emission;
    }

    public void setEmission(String emission) {
        this.emission = emission;
    }
}
