package com.example.Isotel;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

    @GetMapping("/")
    public String Mostrar(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        boolean isAdmin = userDetails != null && userDetails.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        return "index"; //index.html
    }

    @GetMapping("/home")
    public String Mostrar2() {
        return "index"; //index.html
    }
}
