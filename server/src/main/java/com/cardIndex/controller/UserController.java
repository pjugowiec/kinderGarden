package com.cardIndex.controller;

import com.cardIndex.domain.dto.UserDto;
import com.cardIndex.domain.entity.User;
import com.cardIndex.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "localhost:4200")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> getAll(){
        return this.userService.getAllUsers();
    }
}
