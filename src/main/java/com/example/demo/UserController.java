package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request){
        String login = request.login();
        String password = request.password();
        String name = request.name();

        if(login == null || password == null || name == null){
            return ResponseEntity.badRequest().build();
        }

        if(isIncorrectInput(login) || isIncorrectInput(password) || isIncorrectInput(name)){
            return ResponseEntity.badRequest().build();
        }

        if(login.length() > 16 || password.length() > 16 || name.length() >= 255){
            return ResponseEntity.badRequest().build();
        }

        String hashedPassword = passwordService.hashPassword(password);

        User user = new User(login, hashedPassword, name);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public boolean isIncorrectInput(String string){
        return string.isBlank();
    }
}
