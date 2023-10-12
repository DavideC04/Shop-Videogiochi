package org.exercise.ShopVideogiochi.model;

import org.exercise.ShopVideogiochi.security.DatabaseUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

public class Utility {

    public static void addUser(Model model, Authentication authentication) {
        if (authentication != null) {
            DatabaseUserDetails user = (DatabaseUserDetails) authentication.getPrincipal();
            model.addAttribute("user", user);
        }

    }

    public static void addAdmin(Model model, Authentication authentication) {
        if (authentication != null) {
            DatabaseUserDetails admin = (DatabaseUserDetails) authentication.getPrincipal();
            model.addAttribute("admin", admin);
        }
    }
}
