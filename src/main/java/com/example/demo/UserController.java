package com.example.demo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.node.ObjectNode;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public UserController(UserRepository userRepository, PasswordService passwordService){
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody CreateUserRequest request){
        String login = request.login();
        String password = request.password();
        String name = request.name();

        if(login == null || password == null || name == null){
            return;
        }

        if(isIncorrectInput(login) || isIncorrectInput(password) || isIncorrectInput(name)){
            return;
        }

        if(login.length() > 16 || password.length() > 16 || name.length() >= 255){
            return;
        }

        String hashedPassword = passwordService.hashPassword(password);

        User user = new User(login, hashedPassword, name);
        userRepository.save(user);
    }

    public boolean isIncorrectInput(String string){
        return string.isBlank();
    }
}
