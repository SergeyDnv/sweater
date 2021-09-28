package ru.dnsk.sweater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import ru.dnsk.sweater.model.Role;
import ru.dnsk.sweater.model.User;
import ru.dnsk.sweater.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       MailSenderService mailSenderService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailSenderService = mailSenderService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user) {
        User existUser = userRepository.findByUsername(user.getUsername());

        if (existUser != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        sendActivationCode(user);

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        userRepository.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user, String userName, Map<String, String> form) {
        user.setUsername(userName);

        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChange = ((email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email)));

        if (isEmailChange) {
            user.setEmail(email);

            if (StringUtils.hasText(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (StringUtils.hasText(password)) {
            user.setPassword(password);
        }

        userRepository.save(user);

        if (isEmailChange) {
            sendActivationCode(user);
        }
    }

    private void sendActivationCode(User user) {
        if (!ObjectUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s. \n" +
                            "Welcome to Sweater. Visit for activation: http://localhost:8080/active/%s",
                    user.getUsername(),
                    user.getActivationCode());

            mailSenderService.send(user.getEmail(), "Activation code", message);
        }
    }

    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);
        userRepository.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);
        userRepository.save(user);
    }
}
