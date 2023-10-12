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
            purchase.setQuantity(1);
            purchase.setVideogame(videogame);
            purchase.setDateTime(LocalDateTime.now());
            List<User> userList = userRepository.findAll();
            model.addAttribute("purchase", purchase);
            model.addAttribute("users", userList);
            model.addAttribute("game", videogame);

            model.addAttribute("selectedUser", new User());

            return "purchase";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/create/{gameId}")
    public String doCreate(@PathVariable("gameId") Integer id, Model model, @Valid @ModelAttribute("purchase") Purchase form,
                           BindingResult bindingResult, @RequestParam("selectedUser") Integer selectedUserId) {
        if (bindingResult.hasErrors()) {

            Videogame videogame = videogameRepository.findById(id).orElse(null);
            Purchase purchase = new Purchase();
            purchase.setQuantity(1);
            purchase.setVideogame(videogame);
            purchase.setDateTime(LocalDateTime.now());
            model.addAttribute("purchase", purchase);
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("game", videogame);
            model.addAttribute("selectedUser", new User());
            return "purchase";
        }

        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        Optional<User> userOptional = userRepository.findById(selectedUserId);

        if (videogameOptional.isPresent() && userOptional.isPresent()) {
            Videogame videogame = videogameOptional.get();
            User user = userOptional.get();
            model.addAttribute("game", videogame);
            model.addAttribute("users", user);
            purchaseRepository.save(form);
            return "checkout";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}

