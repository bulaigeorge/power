package cegal.power.emission;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "emission")
public class Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private int emission;

    public Emission() {}

    public Emission(String type, int emission) {
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

    public int getEmission() {
        return emission;
    }

    public void setEmission(int emission) {
        this.emission = emission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emission emission1 = (Emission) o;
        return id == emission1.id && emission == emission1.emission && type.equals(emission1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, emission);
    }
}
