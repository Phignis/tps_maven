package fr.ensim.tp3.TP3.controller;

import fr.ensim.tp3.TP3.model.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/addresses")
    public String showAddresses(Model model) {
        model.addAttribute("allAddresses", addressRepository.findAll());
        return "addresses";
    }

    @GetMapping("/giveAddress")
    public String showAddressForm() {
        return "addressForm";
    }

}
