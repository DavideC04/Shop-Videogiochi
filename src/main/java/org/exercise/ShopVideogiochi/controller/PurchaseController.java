package org.exercise.ShopVideogiochi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {


    @GetMapping
    public String index() {
        return "/checkout";
    }
}
