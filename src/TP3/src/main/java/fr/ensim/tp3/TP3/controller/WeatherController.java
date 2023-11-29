package fr.ensim.tp3.TP3.controller;

import fr.ensim.tp3.TP3.model.AddressRepository;
import fr.ensim.tp3.TP3.model.LocationWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {
    @Autowired
    AddressRepository addressRepository;



    @PostMapping("/meteo")
    public String getWeatherAt(@RequestParam(name="address") String address, Model model, RestTemplate restResolver) {
        model.addAttribute("givenAddress", address);
        address = address.replace(" ", "+");
        LocationWeather lw = restResolver.getForObject("https://api-adresse.data.gouv.fr/search/?q="+ address + "&limit=1", LocationWeather.class);
        System.out.println("https://api-adresse.data.gouv.fr/search/?limit=1&q="+ address);
        if(lw != null && lw.isDataFounded()) {
            return "displayWeather";
        } else {
            return "addressNotFound";
        }

    }
}
