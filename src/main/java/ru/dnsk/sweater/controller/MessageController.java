package ru.dnsk.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.dnsk.sweater.model.Message;
import ru.dnsk.sweater.model.User;
import ru.dnsk.sweater.repository.MessageRepository;
import ru.dnsk.sweater.service.MessageService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class MessageController {

    @Value("${upload.path}")
    private String uploadPath;
    private final MessageRepository messageRepository;
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageRepository messageRepository, MessageService messageService) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String searchTag, Model model) {
        List<Message> messageList = messageService.messageList(searchTag);

        model.addAttribute("messageList", messageList);
        model.addAttribute("searchTag", searchTag);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @Valid Message message,
                             BindingResult bindingResult,
                             Model model,
                             @RequestParam(name = "file") MultipartFile file) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            saveFile(message, file);

            model.addAttribute("message", null);

            messageRepository.save(message);
        }

        model.addAttribute("messageList", messageRepository.findAll());
        return "main";
    }

    @GetMapping("/user-messages/{user}")
    public String userMessages(@AuthenticationPrincipal User currentUser,
                               @PathVariable User user,
                               @RequestParam(required = false) Message message,
                               Model model) {
        Set<Message> messageSet = user.getMessages();
        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("messageList", messageSet);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "userMessages";
    }

    @PostMapping("/user-messages/{userId}")
    public String updateUser(@AuthenticationPrincipal User currentUser,
                             @PathVariable Long userId,
                             @RequestParam(name = "id") Message message,
                             @RequestParam(name = "text") String text,
                             @RequestParam(name = "tag") String tag,
                             @RequestParam(name = "file") MultipartFile file) throws IOException {
        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.hasLength(text)) {
                message.setText(text);
            }
            if (!StringUtils.hasLength(tag)) {
                message.setTag(tag);
            }

            saveFile(message, file);
            messageRepository.save(message);
        }

        return "redirect:/user-messages/" + userId;
    }

    private void saveFile(Message message, MultipartFile file) throws IOException {
        if (file != null && !ObjectUtils.isEmpty(file.getOriginalFilename())) {
            File directory = new File(uploadPath);
            if (!directory.exists()) {
                directory.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));

            message.setFileName(resultFileName);
        }
    }
}
