package com.raport.controller;

import com.raport.domain.entity.UserEntity;
import com.raport.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @GetMapping()
//    public ResponseEntity<List<UserEntity>> getAll(){
//
//        return ResponseEntity
//            .status(HttpStatus.ACCEPTED)
//                .body(this.userService.getAllUsers());
//
//    }
}
