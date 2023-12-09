package fr.ensim.tp3.TP3.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*;
import static java.util.Calendar.LONG;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherInformation(@JsonProperty("city") City city, @JsonProperty("forecast") Forecast[] forecastsPredicted,
                                 @JsonProperty("update") Calendar predictionDateForForecast) {
    private record City(@JsonProperty("name") String name) {}


    public String getCityName() {
        return city.name;
    }

    /**
     * get a string representation of the date where forecasts were predicted, in format "DAY dd MONTH yyyy"
     * @return the string representation of the date
     */
    public String getPredictionsDate() {
        return predictionDateForForecast.getDisplayName(DAY_OF_WEEK, LONG, Locale.UK) + " " +
                predictionDateForForecast.get(Calendar.DAY_OF_MONTH) + " " +
                predictionDateForForecast.getDisplayName(MONTH , LONG, Locale.UK) + " " +
                predictionDateForForecast.get(Calendar.YEAR);
    }

}
