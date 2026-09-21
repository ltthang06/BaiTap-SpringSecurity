package vn.iotstar.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import vn.iotstar.mapper.UserMapper;
import vn.iotstar.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/")
    public String home(
            Authentication authentication,
            Model model) {

        addCurrentUser(authentication, model);

        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(
            Authentication authentication,
            Model model) {

        addCurrentUser(authentication, model);

        return "dashboard";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    private void addCurrentUser(
            Authentication authentication,
            Model model) {

        if (authentication == null
                || !authentication.isAuthenticated()) {
            return;
        }

        userRepository
            .findByEmailWithRole(authentication.getName())
            .ifPresent(user ->
                model.addAttribute(
                    "currentUser",
                    userMapper.toDto(user)
                )
            );
    }
}