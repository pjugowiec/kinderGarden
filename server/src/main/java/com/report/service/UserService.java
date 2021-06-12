package com.report.service;

import com.report.domain.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();
    void createUser(final UserEntity userEntity);
    void updateUser(final UserEntity userEntity, final Long id);
    void deleteUser(final Long id);

}
