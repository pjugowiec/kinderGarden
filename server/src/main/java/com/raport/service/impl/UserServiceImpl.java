package com.raport.service.impl;

import com.raport.domain.entity.UserEntity;
import com.raport.repository.UserRepository;
import com.raport.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }


    @Override
    public void createUser(final UserEntity userEntity) {
            this.userRepository.save(userEntity);
    }

    @Override
    public void updateUser(final UserEntity userEntity, final Long id) {
//        UserEntity userFromData = this.userRepository.findById(id)
//                .orElseThrow(Ex);
    }

    @Override
    public void deleteUser(final Long id) {
        this.userRepository.deleteById(id);
    }
}