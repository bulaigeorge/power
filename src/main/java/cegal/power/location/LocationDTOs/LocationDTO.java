package cegal.power.location.LocationDTOs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationDTO(
        String month,
        int year,
        String city,
        String power,
        @JsonProperty("unit_price")
        int unitPrice,
        int units,
        @JsonAlias({"river", "gas_source"})
        String source) {

}
