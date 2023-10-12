package org.exercise.ShopVideogiochi.controller;

import org.exercise.ShopVideogiochi.model.User;
import org.exercise.ShopVideogiochi.model.Utility;
import org.exercise.ShopVideogiochi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/user/show/{userId}")
    public String show(@PathVariable("userId") Integer id, Model model, Authentication authentication) {
        Utility.addUser(model, authentication);
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);

            return "profile";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }


    }


}
