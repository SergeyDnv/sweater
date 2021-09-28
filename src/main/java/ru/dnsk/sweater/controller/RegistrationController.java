package ru.dnsk.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dnsk.sweater.model.User;
import ru.dnsk.sweater.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (user.getPassword() != null && !user.getPassword().equals(user.getPasswordConfirmation())) {
            //model.addAttribute("passwordError", "Passwords are different");
            bindingResult.addError(new FieldError("user", "password", "Passwords are different!"));
            //return "registration";
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);

            return "registration";
        }

        if (!(userService.addUser(user))) {
            model.addAttribute("usernameError", "User exist");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/active/{code}")
    public String activateUser(@PathVariable String code, Model model) {
        boolean isActivate = userService.activateUser(code);

        if (isActivate) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "User not activated");
        }

        return "login";
    }
}
