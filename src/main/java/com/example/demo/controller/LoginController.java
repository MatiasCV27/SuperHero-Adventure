package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model, Principal principal, RedirectAttributes attributes) {

        if (error != null) {
            model.addAttribute("error", "Error de acceso: el usuario y/o la contraseña es incorrecta");
        }

        if (logout != null) {
            model.addAttribute("success", "Usted a cerrado la sesión de forma existosa");
        }

        if (principal != null) {
            attributes.addFlashAttribute("warning", "Usted ya ha iniciado sesión");
            return "redirect:/index";
        }

        return "login";
    }
}
