package com.cardIndex.services;

import com.cardIndex.domain.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void createUser(final User user);
    void updateUser(final User user, final Long id);
    void deleteUser(final Long id);

}
