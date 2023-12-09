package fr.ensim.tp3.TP3.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Forecast(@JsonProperty("datetime") Calendar dateOfForecast, @JsonProperty("probawind100") int probaWindAt100,
                       @JsonProperty("probawind70") int probaWindAt70, @JsonProperty("probarain") int probaRain,
                       @JsonProperty("sunHours") int sunHours, @JsonProperty("weather") int weatherCode,
                       @JsonProperty("tmax") int tMax, @JsonProperty("tmin") int tMin) {
    /**
     * get a string representation of the effect date for the forecast, in format "DAY dd MONTH yyyy"
     * @return the string representation of the date
     */
    public String getForecastDate() {
        return dateOfForecast.getDisplayName(DAY_OF_WEEK, LONG, Locale.UK) + " " +
                dateOfForecast.get(Calendar.DAY_OF_MONTH) + " " +
                dateOfForecast.getDisplayName(MONTH , LONG, Locale.UK) + " " +
                dateOfForecast.get(Calendar.YEAR);
    }
}
