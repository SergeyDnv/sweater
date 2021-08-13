package ru.dnsk.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dnsk.sweater.model.Message;
import ru.dnsk.sweater.model.User;
import ru.dnsk.sweater.repository.MessageRepository;

import java.util.List;

@Controller
public class MainController {

    private final MessageRepository repository;

    @Autowired
    public MainController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        List<Message> messageList = repository.findAll();
        model.addAttribute("messageList", messageList);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam(name = "text") String text,
                             @RequestParam(name = "tag") String tag) {
        repository.save(new Message(user, text, tag));
        return "redirect:/main";
    }

    @PostMapping("/filter")
    public String filterMessageByTag(@RequestParam(name = "search tag") String searchTag, Model model) {
        List<Message> messageList;
        if (searchTag != null && !searchTag.isEmpty()) {
            messageList = repository.findAllByTag(searchTag);
        } else {
            messageList = repository.findAll();
        }

        model.addAttribute("messageList", messageList);
        return "main";
    }
}
