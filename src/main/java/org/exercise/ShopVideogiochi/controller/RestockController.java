package org.exercise.ShopVideogiochi.controller;


import jakarta.validation.Valid;
import org.exercise.ShopVideogiochi.model.Restock;
import org.exercise.ShopVideogiochi.model.Utility;
import org.exercise.ShopVideogiochi.model.Videogame;
import org.exercise.ShopVideogiochi.repository.RestockRepository;
import org.exercise.ShopVideogiochi.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("storage")
public class RestockController {

    @Autowired
    private VideogameRepository videogameRepository;
    @Autowired
    private RestockRepository restockRepository;

    @GetMapping
    public String index(Model model, Authentication authentication) {
        Utility.addUser(model, authentication);
        List<Videogame> videogameList = videogameRepository.findAll();
        List<Restock> restocks = restockRepository.findAll();
        model.addAttribute("game", videogameList);
        return "storage";
    }

    @GetMapping("/add/{gameId}")
    public String add(@PathVariable("gameId") Integer gameId, Model model) {
        Optional<Videogame> videogameOptional = videogameRepository.findById(gameId);
        if (videogameOptional.isPresent()) {
            Videogame videogame = videogameOptional.get();
            Restock restock = new Restock();
            restock.setVideogame(videogame);
            model.addAttribute("restock", restock);
            return "restock";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add/{gameId}")
    public String doAdd(@Valid @ModelAttribute("restock") Restock restock, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "restock";
        } else {

            restock.setDate(LocalDate.now());
            restockRepository.save(restock);
            return "redirect:/storage/add/" + restock.getVideogame().getId();
        }
    }

}
