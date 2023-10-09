package org.exercise.ShopVideogiochi.controller;

import jakarta.validation.Valid;
import org.exercise.ShopVideogiochi.model.Console;
import org.exercise.ShopVideogiochi.model.Purchase;
import org.exercise.ShopVideogiochi.model.Videogame;
import org.exercise.ShopVideogiochi.repository.ConsoleRepository;
import org.exercise.ShopVideogiochi.repository.PurchaseRepository;
import org.exercise.ShopVideogiochi.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

    @GetMapping
    public String index(Model model) {
        List<Videogame> videogameList = videogameRepository.findAll();

        //per recuperare videogiochi più venduti
        List<Purchase> purchaseList = purchaseRepository.findAll();
        Map<Videogame, Integer> popularGamesMap = new HashMap<>();
        Set<Videogame> popularGames = new HashSet<>();


        for (Purchase p : purchaseList) {

            LocalDateTime currDateTime = LocalDateTime.now();
            LocalDateTime purchaseDateTime = p.getDateTime();


            if (purchaseDateTime.getMonth().equals(currDateTime.getMonth()) && purchaseDateTime.getYear() == currDateTime.getYear()) {
                Videogame game = p.getVideogame();

                if (popularGamesMap.containsKey(game)) {
                    popularGamesMap.put(game, popularGamesMap.get(game) + 1);
                } else {
                    popularGamesMap.put(game, 1);
                }

            }
        }

        for (Map.Entry<Videogame, Integer> entry : popularGamesMap.entrySet()) {
            if (entry.getValue() > 3) {
                popularGames.add(entry.getKey());
            }
        }
        System.out.println(popularGamesMap);
        System.out.println(popularGames);

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
        return "redirect:/";
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


    // controller admin
    @GetMapping("/admin")
    public String admin(Model model) {
        List<Videogame> videogameList = videogameRepository.findAll();
        model.addAttribute("game", videogameList);
        return "admin";
    }
}
