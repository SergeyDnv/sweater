package ru.dnsk.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.dnsk.sweater.model.Message;
import ru.dnsk.sweater.model.User;
import ru.dnsk.sweater.repository.MessageRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;
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
    public String main(@RequestParam(required = false, defaultValue = "") String searchTag, Model model) {
        List<Message> messageList;

        if (searchTag != null && !searchTag.isEmpty()) {
            messageList = repository.findAllByTag(searchTag);
        } else {
            messageList = repository.findAll();
        }

        model.addAttribute("messageList", messageList);
        model.addAttribute("searchTag", searchTag);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam(name = "text") String text,
                             @RequestParam(name = "tag") String tag,
                             @RequestParam(name = "file") MultipartFile file) throws IOException {

        Message message = new Message(user, text, tag);

        if (file != null) {
            File directory = new File(uploadPath);
            if (!directory.exists()) {
                directory.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));

            message.setFileName(resultFileName);
        }

        repository.save(message);
        return "redirect:/main";
    }
}
