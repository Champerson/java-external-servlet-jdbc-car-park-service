package com.car.park.service.dao;

import com.car.park.service.model.User;

import java.util.List;

public interface UserDao {

    void create(User user);

    User read(long userId);

    User read(String userLogin);

    List<User> readAll();

    List<User> readAllWithoutAssignment();

    void update(User user);

    void delete(long userId);
}
