package com.cardIndex.services.impl;

import com.cardIndex.domain.dto.UserDto;
import com.cardIndex.domain.entity.User;
import com.cardIndex.repository.UserRepository;
import com.cardIndex.services.UserService;
import com.cardIndex.services.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
//        List<User> users = userRepository.findAll();
//       return UserMapper.INSTANCE.usersToUserDtos(users);
        return this.userRepository.findAll();
    }


    @Override
    public void createUser(User user) {
            this.userRepository.save(user);
    }

    @Override
    public void updateUser(User user, long id) {
//        User userFromData = this.userRepository.findById(id)
//                .orElseThrow(Ex);
    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
