package org.exercise.ShopVideogiochi.controller;

import jakarta.validation.Valid;
import org.exercise.ShopVideogiochi.model.Purchase;
import org.exercise.ShopVideogiochi.model.Videogame;
import org.exercise.ShopVideogiochi.repository.PurchaseRepository;
import org.exercise.ShopVideogiochi.repository.UserRepository;
import org.exercise.ShopVideogiochi.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private VideogameRepository videogameRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        if (videogameOptional.isPresent()) {
            Videogame videogameDB = videogameOptional.get();
            model.addAttribute("game", videogameDB);

            return "checkout";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("purchase", new Purchase());
        return "purchase";
    }

    @PostMapping("/create")
    public String doCreate(Model model, @Valid @ModelAttribute("purchase") Purchase form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "purchase";
        }


        Purchase acquisto = purchaseRepository.save(form);
        redirectAttributes.addAttribute("id", acquisto.getId());

        return "redirect:/purchase/show/" + acquisto.getId();
    }


}
