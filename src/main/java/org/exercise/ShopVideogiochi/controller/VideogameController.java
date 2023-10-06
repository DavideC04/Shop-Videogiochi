package org.exercise.ShopVideogiochi.controller;

import jakarta.validation.Valid;
import org.exercise.ShopVideogiochi.model.Videogame;
import org.exercise.ShopVideogiochi.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class VideogameController {

    @Autowired
    private VideogameRepository videogameRepository;

    @GetMapping
    public String index(Model model) {
        List<Videogame> videogameList = videogameRepository.findAll();
        model.addAttribute("game", videogameList);
        return "homepage";
    }


    // metodo dei dettagli
    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        if (videogameOptional.isPresent()) {
            Videogame videogameDB = videogameOptional.get();
            model.addAttribute("game", videogameDB);

            return "details";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // metodo create
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("game", new Videogame());

        return "form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("game") Videogame gameForm, BindingResult bindingResult) {
        // if bindingResult
        if (bindingResult.hasErrors()) {
            return "form";
        }
        videogameRepository.save(gameForm);
        // redirect
        return "redirect:/";
    }

    // metodo edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Optional<Videogame> result = videogameRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("game", result.get());
            return "form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "videogame with id: " + id + " not found");
        }

    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable("id") Integer id, @Valid @ModelAttribute("game") Videogame gameForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        videogameRepository.save(gameForm);
        return "details";
    }


    // metodo delete
    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        videogameRepository.deleteById(id);
        return "redirect:/";
    }

    //Controller Omar  (NON TOCCARE -> IN FASE DI SVILUPPO)
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
