package com.cardIndex.services;

import com.cardIndex.domain.dto.UserDto;
import com.cardIndex.domain.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user, long id);
    void deleteUser(long id);

}
