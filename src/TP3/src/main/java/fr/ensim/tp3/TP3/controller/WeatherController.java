package fr.ensim.tp3.TP3.controller;

import fr.ensim.tp3.TP3.model.AddressRepository;
import fr.ensim.tp3.TP3.model.json.LocationCoords;
import fr.ensim.tp3.TP3.model.json.WeatherInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {
    @Autowired
    AddressRepository addressRepository;

    private static final String token = "207cb62025b4d87686f0e3f75d180f8c3463014fde80f9bf6bfebe0e6436247c";


    @PostMapping("/meteo")
    public String getWeatherAt(@RequestParam(name="address") String address, Model model, RestTemplate restResolver) {
        model.addAttribute("givenAddress", address);
        address = address.replace(" ", "+");
        LocationCoords lw = restResolver.getForObject("https://api-adresse.data.gouv.fr/search/?q="+ address + "&limit=1", LocationCoords.class);

        try {
            if (lw != null && lw.isDataFounded()) {
                model.addAttribute("givenAddress", lw.getAddress()); // change givenAddress to complete one given by first API
                WeatherInformation w = restResolver.getForObject("https://api.meteo-concept.com/api/forecast/daily?" +
                                "token="+ token + "&latlng=" + lw.getLatitude() +
                                "," + lw.getLongitude(),
                        WeatherInformation.class);
                model.addAttribute("weatherInfo", w);
                return "displayWeather";
            }
        } catch(HttpClientErrorException httpError) {
            // doing nothing, to reach return addressNotFound, because WeatherAPI respond 404 when no data is found
        }

        return "addressNotFound";
    }
}
