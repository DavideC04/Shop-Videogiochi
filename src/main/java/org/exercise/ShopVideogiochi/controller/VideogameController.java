package org.exercise.ShopVideogiochi.controller;

import jakarta.validation.Valid;
import org.exercise.ShopVideogiochi.model.Console;
import org.exercise.ShopVideogiochi.model.Videogame;
import org.exercise.ShopVideogiochi.repository.ConsoleRepository;
import org.exercise.ShopVideogiochi.repository.PurchaseRepository;
import org.exercise.ShopVideogiochi.repository.RestockRepository;
import org.exercise.ShopVideogiochi.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Controller
@RequestMapping("/")
public class VideogameController {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private VideogameRepository videogameRepository;

    @Autowired
    private ConsoleRepository consoleRepository;
    @Autowired
    private RestockRepository restockRepository;

    @GetMapping
    public String index(Model model) {
        List<Videogame> videogameList = videogameRepository.findAll();

        //per recuperare videogiochi più venduti
        List<Object[]> filteredPurchases = purchaseRepository.findPurchasesCurrMonthAndYear();
        Set<Videogame> popularGames = new HashSet<>();

        for (Object[] p : filteredPurchases) {

            Integer gameId = (Integer) p[0];
            Long purchases = (Long) p[1];
            Videogame game = videogameRepository.findById(gameId.intValue()).orElse(null);
            if (purchases > 3) {
                popularGames.add(game);
            }
        }


        //per dividere in base alla console
        Map<Console, List<Videogame>> consoleMap = new HashMap<>();

        for (Videogame v : videogameList) {

            for (Console c : v.getConsoleList()) {

                consoleMap.putIfAbsent(c, new ArrayList<>());

                consoleMap.get(c).add(v);
            }


        }


        model.addAttribute("popular", popularGames);
        model.addAttribute("consoleMap", consoleMap);
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

        List<Console> consoleList = consoleRepository.findAll();
        model.addAttribute("console", consoleList);
        model.addAttribute("game", new Videogame());

        return "form";
    }

    @PostMapping("/create")
    public String doCreate(Model model, @Valid @ModelAttribute("game") Videogame gameForm, BindingResult bindingResult) {


        // if bindingResult
        if (bindingResult.hasErrors()) {
            model.addAttribute("console", consoleRepository.findAll());
            return "form";
        }
        videogameRepository.save(gameForm);
        // redirect
        return "redirect:/admin";
    }

    // metodo edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Optional<Videogame> result = videogameRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("console", consoleRepository.findAll());
            model.addAttribute("game", result.get());
            return "form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "videogame with id: " + id + " not found");
        }

    }

    @PostMapping("/edit/{id}")
    public String doEdit(Model model, @PathVariable("id") Integer id, @Valid @ModelAttribute("game") Videogame gameForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("console", consoleRepository.findAll());
            return "form";
        }
        videogameRepository.save(gameForm);
        return "redirect:/admin";
    }


    // metodo delete
    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        videogameRepository.deleteById(id);
        return "redirect:/admin";
    }

    //Controller Omar  (NON TOCCARE -> IN FASE DI SVILUPPO)
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
