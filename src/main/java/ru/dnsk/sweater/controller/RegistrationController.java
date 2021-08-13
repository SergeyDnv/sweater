package ru.dnsk.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dnsk.sweater.model.Role;
import ru.dnsk.sweater.model.User;
import ru.dnsk.sweater.repository.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {

    private final UserRepository repository;

    @Autowired
    public RegistrationController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User existUser = repository.findByUsername(user.getUsername());

        if (existUser != null) {
            model.addAttribute("existUserMessageError", "User exist");
            return "/registration";
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            repository.save(user);
            return "redirect:/login";
        }
    }
}
