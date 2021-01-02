package com.javierjimenez.springbootdemo.controller;



import com.javierjimenez.springbootdemo.entity.User;
import com.javierjimenez.springbootdemo.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@Validated
@RestController
@RequestMapping("/api/signin")
public class SignInController {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public SignInController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public User signin(@RequestParam String email, @RequestParam String password) {
        User u = new User();
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(password));
        u.setRole(User.Role.USER);
        return repository.save(u);
    }

    @PostMapping("/validateEmail")
    public Boolean emailExists(@RequestParam String email) {
        return repository.existsByEmail(email);
    }

}
