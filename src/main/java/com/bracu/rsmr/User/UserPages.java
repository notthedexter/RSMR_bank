package com.bracu.rsmr.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPages {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user-home")
    public String userHome(Model model) {
        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
        String username = authenticated.getName();
        User user = userRepository.findByUsername(username).get();
        model.addAttribute("user", user);
        List<String> roles = user.getRoles();
        if (roles.contains("Customer"))
            return "index";
        else
            return "mindex";
    }

    @GetMapping("/trans")
    public String transfer(Model model) {
        return "transfer";
    }

    @GetMapping("/modpage")
    public String modMg(Model model) {
        List<User> allUsers = userRepository.findAll();

        List<User> filteredUsers = allUsers.stream()
                .filter(user -> !user.getRoles().contains("Moderator"))
                .collect(Collectors.toList());
        model.addAttribute("users", filteredUsers);
        return "modPage";
    }
}
