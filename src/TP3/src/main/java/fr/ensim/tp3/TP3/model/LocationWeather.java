package fr.ensim.tp3.TP3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationWeather(@JsonProperty("features") Feature[] features) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private record Feature(@JsonProperty("geometry") Geometry g, @JsonProperty("properties")Property p) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record Geometry(@JsonProperty("coordinates") float[] coords){}

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record Property(@JsonProperty("label") String address){}

    public boolean isDataFounded() {
        return features.length != 0;
    }

    public float getXCoord() {
        return features[0].g.coords[0];
    }
    public float getYCoord() {
        return features[0].g.coords[1];
    }

    public String getAddress() {
        return features[0].p.address;
    }
}




