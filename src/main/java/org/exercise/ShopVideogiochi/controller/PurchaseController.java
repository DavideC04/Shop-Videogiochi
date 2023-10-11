package org.exercise.ShopVideogiochi.controller;

import jakarta.validation.Valid;
import org.exercise.ShopVideogiochi.model.Purchase;
import org.exercise.ShopVideogiochi.model.User;
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

import java.time.LocalDateTime;
import java.util.List;
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

    @GetMapping("/create/{id}")
    public String create(@PathVariable("id") Integer id, Model model) {
        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        if (videogameOptional.isPresent()) {
            Videogame videogame = videogameOptional.get();
            Purchase purchase = new Purchase();
            purchase.setVideogame(videogame);
            purchase.setDateTime(LocalDateTime.now());
            List<User> userList = userRepository.findAll();
            model.addAttribute("purchase", purchase);
            model.addAttribute("users", userList);
            model.addAttribute("game", videogame);
            return "purchase";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create/{gameId}")
    public String doCreate(@PathVariable("gameId") Integer id, Model model, @Valid @ModelAttribute("purchase") Purchase form,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "purchase";
        }
        System.out.println(form.getId());
        purchaseRepository.save(form);
        return "checkout";
    }
}

