package com.cardIndex.services.impl;

import com.cardIndex.domain.entity.User;
import com.cardIndex.repository.UserRepository;
import com.cardIndex.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }


    @Override
    public void createUser(final User user) {
            this.userRepository.save(user);
    }

    @Override
    public void updateUser(final User user, final Long id) {
//        User userFromData = this.userRepository.findById(id)
//                .orElseThrow(Ex);
    }

    @Override
    public void deleteUser(final Long id) {
        this.userRepository.deleteById(id);
    }
}
