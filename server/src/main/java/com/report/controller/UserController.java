package com.report.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
