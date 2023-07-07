package com.example.Isotel.Login;

import com.example.Isotel.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new Usuario());
        return "Login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Usuario user, Model model) {
        Usuario existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            if (user.isAdmin()) {
                model.addAttribute("message", "Admin login successful");
            } else {
                model.addAttribute("message", "User login successful");
            }
        } else {
            model.addAttribute("message", "Invalid username or password");
        }

        return "Login";
    }
}
